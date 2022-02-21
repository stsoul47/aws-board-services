package com.jslee.awsboardapiserver.project.repository.impl;


import com.jslee.awsboardapiserver.project.domain.DUser;
import com.jslee.awsboardapiserver.project.dto.UserManagement.UserManagementQueryDTO;
import com.jslee.awsboardapiserver.project.repository.UserManagementRepositoryCustom;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.jslee.awsboardapiserver.project.domain.QDUser.dUser;

@Repository
public class UserManagementRepositoryImpl implements UserManagementRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public UserManagementRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<DUser> findAll(Pageable pageable, UserManagementQueryDTO queryDTO) {
        QueryResults<DUser> userManagementQueryResults = queryFactory.selectFrom(dUser)
                .where(eqName(queryDTO.getUserName()))
                .orderBy(
                        getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(userManagementQueryResults.getResults(), pageable, userManagementQueryResults.getTotal());
    }

    private BooleanExpression eqName(String name) {
        if(StringUtils.isEmpty(name)) {
            return null;
        }
        return dUser.userName.eq(name);
    }


    private List<OrderSpecifier> getOrderSpecifier(Sort sort) {
        List<OrderSpecifier> orders = new ArrayList<>();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();
            PathBuilder orderByExpression = new PathBuilder(UserManagementQueryDTO.class, prop);
            orders.add(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });
        return orders;
    }

}
