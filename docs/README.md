# &nbsp;&nbsp;[미션 - 크리스마스]

---

## &nbsp;&nbsp;구조

- Model ()
- View (inputView, outputView)
- Controller (X-masController, BookingController, MenuController)
- DTO()
- constant()
---

### &nbsp;VIEW

- 사용자와 직접적으로 상호작용을 하는 계층
- [x] 콘솔로부터 사용자의 입력을 받는 InputView
- [ ] 콘솔에 사용자에게 메시지를 출력하는 OutputView
- [ ] 입력 값의 유효 처리가 완료된 뒤 Controller에게 전달
- [ ] 입력 값이 유효하지 않은 경우 에러 메시지를 출력

### &nbsp;&nbsp;InputView 기능 명세서

- [ ] 예약 날짜를 입력받는다.

### &nbsp;&nbsp;OutputView 기능 명세서
#### &nbsp;&nbsp;예약 날짜 입력

- [ ] 예약 날짜를 입력해주세요.
- 
### &nbsp;&nbsp;유효성 검사

- [ ] 예약 날짜가 숫자가 아닌 값이 포함된 경우
- [ ] 예약 날짜가 1~31 사이의 값이 아닌 경우