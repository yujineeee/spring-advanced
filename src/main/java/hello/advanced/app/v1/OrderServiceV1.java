package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;

    private final HelloTraceV1 trace;

    public void orderItem(String itemId) {
        TraceStatus begin = null;
        try {
            begin = trace.begin("service.orderItem()");
            orderRepository.save(itemId);
            trace.end(begin);
        } catch(Exception e) {
            trace.exception(begin, e);
            throw e; //예외 던져줘야함
        }
    }

}
