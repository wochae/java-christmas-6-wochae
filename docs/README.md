# &nbsp;&nbsp;[미션 - 크리스마스]

---

## &nbsp;&nbsp;구조

- Model (Parser, Booking, MenuSearch)
- View (inputView, outputView)
- Controller (X-masController, ReservationController, BookingController, MenuController)
- DTO(MenuItem, MenuType)
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

#### &nbsp;&nbsp;BookingWriterView 최종 출력

- 입력받은 날짜를 12월 + 해당 일자로 출력한다.
- 입력받은 메뉴와 수량을 출력한다.

### &nbsp;&nbsp;유효성 검사 리스트

- [x] 예약 날짜가 숫자가 아닌 값이 포함된 경우
- [x] 예약 날짜가 1~31 사이의 값이 아닌 경우


- [x] 주문과 수량 배열 안에 요소가 공백이 포함된 경우
- [x] 주문과 수량 배열 안에 1번째 요소가 숫자가 아닌 값이 포함된 경우
- [x] 음료만 주문 할 수 없다.
- [x] 하나에 21개의 음식을 주문할 수 없다.
- [x] 음식 총 합이 20개 이하여야한다.
- [x] 주문과 수량 배열 안에 0번째 요소가 지정된 음식 타입이 아닌 경우
