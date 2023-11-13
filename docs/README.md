# &nbsp;&nbsp;[미션 - 크리스마스]

---

## &nbsp;&nbsp;구조

- Model (Parser, Booking)
- View (inputView, outputView)
- Controller (X-masController, ReservationController, BookingController, MenuController)
- DTO()
- constant(Message, Constant)
---

## &nbsp;계층

- X-masController
    - ReservationController
        - BookingController
          - inputView
           - outputView
        - MenuController
          - inputView
          - outputView
    - domain
        - service
          - Parser
        - Booking
        - Menu
        - Message
    - exception
      - PlannerException, ErrorMessage
      - 
### &nbsp;VIEW

- 사용자와 직접적으로 상호작용을 하는 계층
- [x] 콘솔로부터 사용자의 입력을 받는 InputView
- [x] 콘솔에 사용자에게 메시지를 출력하는 OutputView
- [x] 입력 값의 유효 처리가 완료된 뒤 Controller에게 전달
- [x] 입력 값이 유효하지 않은 경우 에러 메시지를 출력

### &nbsp;&nbsp;InputView 기능 명세서

- [x] 예약 날짜를 입력받는다.

### &nbsp;&nbsp;OutputView 기능 명세서
#### &nbsp;&nbsp;예약 날짜 입력

- [x] 예약 날짜를 입력해주세요.



### &nbsp;&nbsp;유효성 검사 리스트

- [x] 예약 날짜가 숫자가 아닌 값이 포함된 경우
- [x] 예약 날짜가 1~31 사이의 값이 아닌 경우


- [ ] 주문과 수량 배열 안에 요소가 공백이 포함된 경우
- [ ] 주문과 수량 배열 안에 1번째 요소가 숫자가 아닌 값이 포함된 경우
