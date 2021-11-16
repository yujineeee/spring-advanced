package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;

    private final LogTrace trace;

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
