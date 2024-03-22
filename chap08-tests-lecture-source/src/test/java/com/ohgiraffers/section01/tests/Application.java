package com.ohgiraffers.section01.tests;

import com.ohgiraffers.section01.tests.Calculator;

public class Application {

    public static void main(String[] args) {

        /* 필기. 테스트 시나리오 */
        /* 필기. 애플리케이션의 테스트 되어야할 기능이나 특징을 한 문장으로 기술한 것 */
        /* 순서 1. Calculator 인스턴스 생성이 잘 되는지 테스트 */
        com.ohgiraffers.section01.tests.Calculator calc = new Calculator();

        if(calc != null) {
            System.out.println("성공");
        } else {
            System.out.println("실패");
        }

        /* 순서 2. sumTwoNumber 메소드가 정상 기능 하는지 테스트 */
        /* 순서 2-1. 4와 5을 전달하면 합계 9가 계산되는지 확인 */
        int result1 = calc.sumTwoNumber(4, 5);

        if(result1 == 9) {
            System.out.println("4와 5를 전달하여 합계가 9인지 확인");
        } else {
            System.out.println("4와 5를 전달하여 합계가 9가 아님");
        }

        /* 순서 2-2. 6과 7을 전달하면 합계가 13이 되는지 확인 */
        int result2 = calc.sumTwoNumber(6, 7);

        if(result2 == 13) {
            System.out.println("6과 7을 전달하여 합계가 13인지 확인");
        } else {
            System.out.println("6과 7을 전달하여 합계가 13이 아님");
        }

        /* 순서 3. 뒤 테스트 결과가 모두 통과되면 해당 클래스의 메소드는 신뢰성 있는 메소드임을 확인 */
        if(result1 == 9 && result2 == 13) {
            System.out.println("테스트 성공! 유후! 난 천재 개발자~~");
        } else {
            System.out.println("테스트 실패! 다시 메소드를 확인해주세요");
        }

    }

}
