package com.kusd.KUmap.domain.search.service;

import com.kusd.KUmap.domain.search.domain.Field_V1;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.request.DetailFieldGetV2Request;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.request.SmallFieldGetV2Request;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.AllFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.AllFieldGetResponses;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.DetailFieldGetResponse;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.MiddleFieldGetV2Response;
import com.kusd.KUmap.domain.search.dto.fieldSearch.v2.response.SmallFieldGetV2Response;
import com.kusd.KUmap.domain.search.repository.FieldSearchV2Repository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldSearchV2Service implements FieldSearchService {

    private final FieldSearchV2Repository fieldSearchV2Repository;
    private final FieldValidateChecker FieldValidateChecker;

//    @Cacheable("fieldList")
//    public List<AllFieldGetResponse> getAllFieldList() {
//        AllFieldGetResponses allFieldGetResponses = new AllFieldGetResponses();
//        fieldSearchV2Repository.findAllWithNonEmptyDetailField().stream()
//                .filter(FieldValidateChecker::validator)
//                .forEach(allFieldGetResponses::addAllFieldGetResponse);
//        return allFieldGetResponses.getAllFieldGetResponses();
//    }
//
//    @Cacheable("MiddleFieldList")
//    public List<MiddleFieldGetV2Response> getMiddleFieldList() {
//        //모든 세분류를 가져와서 유효성 검사를 통과한 middleField만 가져온다.
//        return fieldSearchV2Repository.findAllWithNonEmptyDetailField().stream()
//                .filter(FieldValidateChecker::validator)
//                .map(Field_V1::getMiddleField)
//                .distinct()
//                .map(MiddleFieldGetV2Response::from)
//                .toList();
//    }
//
//    @Cacheable("SmallFieldList")
//    public List<SmallFieldGetV2Response> getSmallFieldList(SmallFieldGetV2Request request) {
//        //중분류에 속하는 모든 세분류를 가져와서 유효성 검사를 통과한 중분류 속 소분류만 가져온다.
//        return fieldSearchV2Repository.findAllByMiddleFieldAndNonEmptyDetailField(request.middleField()).stream()
//                .filter(FieldValidateChecker::validator)
//                .map(Field_V1::getSmallField)
//                .distinct()
//                .map(smallField -> SmallFieldGetV2Response.from(request.middleField(), smallField))
//                .toList();
//    }
//
//    @Cacheable("DetailFieldList")
//    public List<DetailFieldGetResponse> getDetailFieldList(DetailFieldGetV2Request request) {
//        return fieldSearchV2Repository.findAllByMiddleFieldAndSmallField(request.middleField(), request.smallField()).stream()
//                .filter(Field_V1::hasDetailField)
//                .filter(FieldValidateChecker::validator)
//                .map(DetailFieldGetResponse::from)
//                .toList();
//    }
}
