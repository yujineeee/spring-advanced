package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderService;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        TraceStatus begin = null;
        try {
            begin = trace.begin("request()");
            orderService.orderItem(itemId);
            trace.end(begin);
            return "ok";
        } catch(Exception e) {
            trace.exception(begin, e);
            throw e; //예외 던져줘야함
        }
    }

}
