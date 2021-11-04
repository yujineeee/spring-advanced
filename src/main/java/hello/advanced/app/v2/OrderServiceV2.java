package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;

    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus begin = null;
        try {
            begin = trace.beginSync(traceId,"service.orderItem()");
            orderRepository.save(begin.getTraceId(), itemId);
            trace.end(begin);
        } catch(Exception e) {
            trace.exception(begin, e);
            throw e; //예외 던져줘야함
        }
    }

}
