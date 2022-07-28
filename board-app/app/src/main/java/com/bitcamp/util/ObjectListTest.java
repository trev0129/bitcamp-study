package com.bitcamp.util;

public class ObjectListTest {
  public static void main(String[] args) {
    class Member {}

    ObjectList list = new ObjectList();
    list.add("홍길동");
    list.add("홍길은");
    list.add("홍길순");
    list.add(null);
    list.add("안중근");

    ;
    System.out.println(list.get(0)); 
    System.out.println(list.get(1));
    System.out.println(list.get(2));

    //파라미터 값이 유효한 인스턴스 주소라면 toString을호출하여 그 리턴 값을 출력한다.
    //따라서 굳이 toString()을 명시적을 호출할 필요가 없다.
    System.out.println(list.get(4));
    System.out.println(list.get(4));
    // 파라미터 값이 null이라면 그대로 null을 출력한다.
    System.out.println(list.get(3)); // println()에 전달하는 값이 null이면 "null"이라고 출력한다.

    System.out.println(list.get(100));
    // 유효한 인덱스가 아니기 떄문에get() 메서드가 null을 리턴한다.

    // 생각해 볼 문제
    // get() null을 리턴한다면, 
    // 예외 상황인가? 정상적인 상황인가?
    // 결론 ! 
    // null을 리턴한다고 해서 무조건 예외 상황이 아니다.
    // 의도적으로 null을 저장해 놓고 꺼낼 수 있기 때문이다.
    // 따라서 인덱스를 잘못 지정한 경우와 구분해야 한다.
    // 인덱스를 잘 못 지정했으면 예외상황이지만,
    // 그 밖에는 정상적인 상황이다.

    //리턴 값으로는 정상과 예외 상황을 표현할 수 없다.
    // 리턴 값으로 두 상황을 구분할 수 없다.

    //해결책
    //정상적일 경우에는 정상적을 ㅗ해당 값으 리턴한다.
    //예외 상황일 경우에는 예외 정보를던진다. 예외를 발생시킨다.



  }
}
