package xin.lowang.token.link.server;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class ReactTest {
    public static void main(String[] args) {
        //generate();
        //create();
        //mono();
        buffer();
    }

    /**
     * sink中只能调用一次next
     */
    public static void generate() {
        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);
        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
    }

    public static void create() {
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }

    public static void mono() {
        Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
        Mono.justOrEmpty(Optional.empty()).subscribe(System.out::println);
        System.out.println("--------------------------------------");
        Mono.justOrEmpty(null).subscribe(System.out::println);
        System.out.println("--------------------------------------");
        Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);
    }

    public static void buffer() {
        //产生100个数字，每收集到20个输出一次
        //Flux.range(1, 100).buffer(20).subscribe(System.out::println);
        //每隔100ms产生一个数字，每搜集1001ms，取前2次的收集，转换为block线程的stream打印
        //Flux.interval(Duration.ofMillis(100)).log().buffer(Duration.ofMillis(1001)).log().take(2).log().toStream().forEach(System.out::println);
        //产生10个数字，每搜集到i，当i满足predicate，则把刚才搜集到的所有数据加入list，并开始下一次搜集
        //Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).log().subscribe(System.out::println);
        //产生10个数字，没搜集到i，当i满足predicate时，则把刚才搜集到的所有数据加入lit，当i不满足predicate时，则立即产生一个新list来搜集
        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
        //产生10个数字，没搜集到i，当i满足predicate时，则把刚才搜集到的所有数据加入lit，当i不满足predicate时，则立即产生一个新list来搜集
        Flux.range(1, 10).bufferWhile(i -> i == 3 || i == 4).subscribe(System.out::println);
    }
}
