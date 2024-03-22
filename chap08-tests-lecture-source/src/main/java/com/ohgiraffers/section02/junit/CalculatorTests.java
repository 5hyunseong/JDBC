package com.ohgiraffers.section02.junit;
/*
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
*/
/* 필기. 단위 테스트란
 *  한가지 기능(함수)마다 일을 잘 수행하는지 확인하며 특정 모듈이 의도된 대로 정확히 작동하는지 검증하는 절차
 *  연관 컴포넌트가 개발되지 않더라도 기능별 개발이 완료된 것을 증명할 수 있음
 * */
/* 주의사항. java JDK 8버전일 경우
 *      Project의 properties -> java build path -> libraries -> add library -> junit(junit5)
 * 주의사항. java JDK 11버전일 경우
 *      Project의 properties -> java build path -> libraries -> libraries 내의 classpath를 클릭한 후에 -> add library -> junit(junit5)
 * */

public class CalculatorTests {

    private Calculator calc = null;

    /* 설명. @Before : @Test가 작성된 메소드 호출 이전에
     *                반복되는 준비 작업을 위한 메소드에 작성
     *                테스트 메소드를 실행하기 전에 먼저 자동으로 실행
     *
     * 설명. @After : @Test가 작성된 메소드 호출 이후 실행
     * */

    /* 필기. 테스트 시나리오 */
    /* 순서 1. Caculator 인스턴스 생성이 잘 되는지 테스트 */
//	@Test			// org.junit.Test을 import
//    @Before
    public void setup() {
        System.out.println("calculator 인스턴스 생성");
        calc = new Calculator();
    }

    /* 순서 2. sumTwoNumber 메소드가 정상 기능 하는지 테스트 */
    /* 순서 2-1. 4와 5를 전달하면 합계 9가 계산 되는지 확인 */
//    @Test
    public void testSumTwoNumber_4와_5를_전달하면_합계가_9가_계산되는지_확인() {

        System.out.println("2-1 테스트 동작");
        int result = calc.sumTwoNumber(4, 5);

//        assertEquals(9, result);
    }

    /* 순서 2-2. 6과 7을 전달하면 합계가 13이 되는지 확인 */
//    @Test
//	@Ignore
    public void testSumTwoNumber_6과_7을_전달하면_합계가_13이_되는지_확인() {

        System.out.println("2-2 테스트 동작");
        int result = calc.sumTwoNumber(6, 7);

//        assertEquals(12, result, 1);
    }

    /* 순서 3. 위의 테스트 결과가 모두 통과하면 해당 클래스의 메소드는 신뢰성 있는 메소드임을 확인 */
//    @After
    public void afterTest() {
        System.out.println("단위 테스트 완료!");
    }

	/* 필기. assert method
	 * assertArrayEquals(a,b) : 배열 a와 b가 일치함을 확인
	 * assertEquals(a,b) : 객체 a와 b의 값이 같은지 확인
	 * assertSame(a,b) : 객체 a와 b가 같은 객체임을 확인
	 * assertTrue(a) : a가 참인지 확인
	 * assertNotNull(a) : a 객체가 null이 아님을 확인
	 * 이외에도 다양한 단정문이 존재
     * http://junit.sourceforge.net/javadoc/org/junit/Assert.html
	 * */

}
