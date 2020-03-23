package com.huisam.springstudy.mapper;

import com.huisam.springstudy.mapper.Order.OrderBuilder;
import com.huisam.springstudy.mapper.OrderDto.OrderDtoBuilder;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-16T22:27:49+0900",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (AdoptOpenJDK)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order orderDtoToEntity(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        OrderBuilder order = Order.builder();

        order.name( orderDto.getName() );
        order.product( orderDto.getProduct() );
        order.price( orderDto.getPrice() );
        order.address( orderDto.getAddress() );
        order.orderedTime( orderDto.getOrderedTime() );

        order.id( (long) 0L );

        return order.build();
    }

    @Override
    public OrderDto orderToDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDtoBuilder orderDto = OrderDto.builder();

        orderDto.name( order.getName() );
        orderDto.product( order.getProduct() );
        orderDto.price( order.getPrice() );
        orderDto.address( order.getAddress() );
        orderDto.orderedTime( order.getOrderedTime() );

        orderDto.img( order.getProduct() + ".jpg" );

        return orderDto.build();
    }
}
