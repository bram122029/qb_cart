//package com.example.cart.order.controller;
//import com.example.cart.order.dtos.OrderDTO;
//import com.example.cart.order.entities.Order;
//import com.example.cart.order.entities.OrderItems;
//import com.example.cart.order.repository.OrderRepository;
//import com.example.cart.order.services.OrderService;
//import com.example.cart.order.services.ServImpl.OrderItemSequenceGeneratorServ;
//import com.example.cart.order.services.ServImpl.OrderSequnceGeneratorServ;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/OrderController")
//public class OrderController {
//
//    @Autowired
//    OrderService orderService;
//
//    @Autowired
//    OrderRepository orderRepository;
//
////    @Autowired
////    OrderItemService orderItemService;
//
//    @Autowired
//    OrderSequnceGeneratorServ orderSequnceGeneratorServ;
//
//
//    @PostMapping("/addOrder")
//    public ResponseEntity<Order> insertToCart(@RequestBody OrderDTO orderDTO) {
//
//        Order order = new Order();
//        //orderItems.setOrderItemId(orderItemSequenceGeneratorServ.generateSequence(OrderItems.SEQUENCE_ITEM_NAME));
//        order.setOrderId(orderSequnceGeneratorServ.generateSequence(Order.SEQUENCE_NAME));
//        BeanUtils.copyProperties(orderDTO, order);
//        return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/deleteByOrderId/{orderId}")
//    public ResponseEntity<String> deleteByStudentId(@PathVariable String orderId){
//        String status=orderService.deleteOrderById(orderId);
//        return new ResponseEntity<String>(status,HttpStatus.OK);
//    }
//    @GetMapping("/findAll")
//    public ResponseEntity<List<Order>> findAll(){
//
//        List<Order> orderList= orderService.findAll();
//        List<OrderDTO> orderDTOList=new ArrayList<>();
//        for(Order order: orderList){
//            OrderDTO orderDTO=new OrderDTO();
//            BeanUtils.copyProperties(order,orderDTO);
//            orderDTOList.add(orderDTO);
//        }
//        return new ResponseEntity(orderDTOList,HttpStatus.OK);
//    }
//
//    @GetMapping("/findByOrderId/{orderId}")
//    public ResponseEntity<Object> findByOrderID(@PathVariable("orderId") String orderId) {
//        Order order = orderService.findByOrderId(orderId);
////        Order order1=(Order)order;
////        if (orderRepository.existsById(orderId)) {
////            OrderDTO orderDTO = new OrderDTO();
////            System.out.println("Heyyyyyyy111");
//////            Order o=new Order();
////
////            BeanUtils.copyProperties(order,orderDTO);
////            System.out.println(orderDTO.getOrderId());
////            System.out.println(order);
////            System.out.println("Heyyyyyyy22222");
//            return new ResponseEntity<Object>(order, HttpStatus.OK);
////        }
////        return new ResponseEntity<Object>("NOT Found", HttpStatus.NOT_FOUND);
//    }
//
////    @PostMapping("/insert_to_OrderItems")
////    public ResponseEntity<OrderItems> insertToCart(@RequestBody OrderItems orderItems) {
//////
//////        Order order = new Order();
//////
//////        order.setOrderId(orderSequnceGeneratorServ.generateSequence(Order.SEQUENCE_NAME));
//////        BeanUtils.copyProperties(orderDTO, order);
////        OrderItems order1 = orderItemService.addToOrderItems(orderItems);
////        return new ResponseEntity<OrderItems>(order1, HttpStatus.CREATED);
////    }
//}
