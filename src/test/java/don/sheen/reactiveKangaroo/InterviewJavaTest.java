package don.sheen.reactiveKangaroo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;

//completely unrelated tests that I made for something else
public class InterviewJavaTest {

    @Test
    public void test1() throws InterruptedException {
       int n = 45;
       System.out.println(solution(n));
    }

    private int solution(int n) {

        if(n == 1)
            return 1;
        else if (n == 2)
            return 2;
        else {
            int first = 1, second = 2, curr = 0;
            for (int i = 2; i < n; i++) {
                curr = first + second;
                first = second;
                second = curr;
            }
            return curr;
        }
    }

    int solution1(int[] a) {
        for(int i=0;i<a.length;i++)
            if(a[Math.abs(a[i])-1]<0)
                return Math.abs(a[i]);
            else{
                a[Math.abs(a[i])-1]=-a[Math.abs(a[i])-1];
            }
        return -1;
    }

}
