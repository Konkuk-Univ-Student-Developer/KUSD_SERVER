package com.kusd.KUmap.domain.search.service;

import com.kusd.KUmap.domain.search.domain.Field_V2;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.request.DetailFieldGetV2Request;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.request.SmallFieldGetV2Request;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.AllFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.AllFieldGetResponses;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.DetailFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.MiddleFieldGetV2Response;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.SmallFieldGetV2Response;
import com.kusd.KUmap.domain.search.repository.FieldV2SearchRepository;
import com.kusd.KUmap.global.error.exception.ErrorCode;
import com.kusd.KUmap.global.error.exception.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldV2SearchService implements FieldSearchService {

    private final FieldV2SearchRepository fieldV2SearchRepository;
    private final FieldValidateChecker FieldValidateChecker;
    private static final int SMALL_FIELD_DEPTH = 3;
    private static final int DETAIL_FIELD_DEPTH = 4;

    @Cacheable("fieldList")
    public Set<AllFieldGetResponse> getAllFieldList() {

        List<Field_V2> detailFields = fieldV2SearchRepository.findAllFieldByCategoryDepth(DETAIL_FIELD_DEPTH);
        List<Field_V2> validateDetailFields = new ArrayList<>();

        for (Field_V2 detailField : detailFields) {
            if (FieldValidateChecker.validator(detailField)) {
                validateDetailFields.add(detailField);
            }
            Field_V2 smallField = fieldV2SearchRepository.findByFieldCode(detailField.getParentFieldCode())
                    .orElseThrow(() -> new NotFoundException(ErrorCode.FIELD_NOT_FOUND));
            if(!FieldValidateChecker.validator(smallField))
                continue;
            validateDetailFields.add(Field_V2.builder()
                    .fieldCode(smallField.getFieldCode())
                    .name("공통")
                    .categoryDepth(DETAIL_FIELD_DEPTH)
                    .parentFieldCode(smallField.getFieldCode())
                    .build());
        }

        AllFieldGetResponses allFieldGetResponses = new AllFieldGetResponses();
        for (Field_V2 validateDetailField : validateDetailFields) {
            Field_V2 smallField = fieldV2SearchRepository.findByFieldCode(validateDetailField.getParentFieldCode())
                    .orElseThrow(() -> new NotFoundException(ErrorCode.FIELD_NOT_FOUND));
            Field_V2 middleField = fieldV2SearchRepository.findByFieldCode(smallField.getParentFieldCode())
                    .orElseThrow(() -> new NotFoundException(ErrorCode.FIELD_NOT_FOUND));
            allFieldGetResponses.addAllFieldGetResponse(middleField, smallField, validateDetailField);
        }

        return allFieldGetResponses.getAllFieldGetResponses();
    }

    @Cacheable("MiddleFieldList")
    public List<MiddleFieldGetV2Response> getMiddleFieldList() {
        //모든 소분류, 세분류를 가져와서 유효성 검사를 통과한 middleField만 가져온다.
        List<Field_V2> middleFields = fieldV2SearchRepository.findAllFieldByCategoryDepth(2);

        List<List<Field_V2>> smallFieldsInMiddleFields = middleFields.stream()
                .map(fieldV2 -> fieldV2SearchRepository.findAllByParentFieldCode(fieldV2.getFieldCode()))
                .toList();

        List<Field_V2> validateMiddleFields = new ArrayList<>();

        int k = 0;
        for (List<Field_V2> smallFieldsInMiddleField : smallFieldsInMiddleFields) {
            boolean isAnyMatchValidate = smallFieldsInMiddleField.stream()
                    .map(fieldV2 -> fieldV2SearchRepository.findAllByParentFieldCode(fieldV2.getFieldCode()))
                    .flatMap(List::stream)
                    .anyMatch(FieldValidateChecker::validator);

            if (isAnyMatchValidate) {
                validateMiddleFields.add(middleFields.get(k));
            }
            k++;
        }

        return validateMiddleFields.stream()
                .map(MiddleFieldGetV2Response::from)
                .toList();
    }

    @Cacheable("SmallFieldList")
    public List<SmallFieldGetV2Response> getSmallFieldList(SmallFieldGetV2Request request) {
        //중분류에 속하는 모든 세분류를 가져와서 유효성 검사를 통과한 중분류 속 소분류만 가져온다.
        List<Field_V2> smallFields = fieldV2SearchRepository.findAllSmallFieldByCategoryDepthAndParentFieldCode(3,
                request.middleFieldCode());

        List<List<Field_V2>> detailFieldsInSmallFields = smallFields.stream()
                .map(fieldV2 -> fieldV2SearchRepository.findAllByParentFieldCode(fieldV2.getFieldCode()))
                .toList();

        List<Field_V2> validateSmallFields = new ArrayList<>();

        int k = 0;
        for (List<Field_V2> detailFieldsInMiddleField : detailFieldsInSmallFields) {
            boolean isAnyMatchValidate = detailFieldsInMiddleField.stream()
                    .anyMatch(FieldValidateChecker::validator);

            if (isAnyMatchValidate) {
                validateSmallFields.add(smallFields.get(k));
            }
            k++;
        }

        return validateSmallFields.stream()
                .map(smallField -> SmallFieldGetV2Response.from(request.middleField(), smallField))
                .toList();
    }

    @Cacheable("DetailFieldList")
    public List<DetailFieldGetResponse> getDetailFieldList(DetailFieldGetV2Request request) {
        Field_V2 smallField = fieldV2SearchRepository.findByFieldCode(request.smallFieldCode())
                .orElseThrow(() -> new NotFoundException(ErrorCode.FIELD_NOT_FOUND));
        List<Field_V2> detailFieldsInSmallFields = fieldV2SearchRepository.findAllSmallFieldByCategoryDepthAndParentFieldCode(
                DETAIL_FIELD_DEPTH, request.smallFieldCode());

        List<Field_V2> validateDetailFields = new ArrayList<>();

        for (Field_V2 detailField : detailFieldsInSmallFields) {
            if (FieldValidateChecker.validator(detailField)) {
                validateDetailFields.add(detailField);
            }
        }

        List<DetailFieldGetResponse> responses = new ArrayList<>(validateDetailFields.stream()
                .map(detailField -> DetailFieldGetResponse.from(request, detailField))
                .toList());
        responses.add(0, DetailFieldGetResponse.from(request, smallField));

        return responses;
    }
}
