## useCase
![usecase](https://user-images.githubusercontent.com/118980125/209763679-436c4622-6d89-445d-8c08-973360b62c88.jpg)

## api명세서
| Method | URL         | Request                                                                                   | Response                                                                                                                                                                                                                                                     |
|--------|-------------|-------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET    | /posts      |                                                                                           | {<br>{"names": "tests2",<br>"titles": "titles2",<br>"contents": "contents2",<br>"modifiedAt": "2022-12-28T10:58:33.744179"},<br>{"names": "tests",<br>"titles": "titles", <br>"contents": "contents",<br>"modifiedAt": "2022-12-28T10:58:20.945885"} … <br>} |
| GET    | /posts/{id} |                                                                                           | {"names": "tests",<br>"titles": "titles",<br>"contents": "contents",<br>"modifiedAt": "2022-12-28T10:58:20.945885"}                                                                                                                                          |
| POST   | /posts      | {"names":"tests",<br>"titles":"titles",<br>"contents":"contents",<br>"passwords":123456}  | {"createdAt": "2022-12-28T11:04:05.6769599",<br>"modifiedAt": "2022-12-28T11:04:05.6769599",<br>"id": 1,<br>"names": "tests",<br>"passwords": 123456,<br>"titles": "titles",<br>"contents": "contents"}                                                      |
| PUT    | /posts/{id} | {"names":"tests",<br>"titles":"titles",<br>"contents":"contents",<br>"passwords":123456 } | {"names": "tests1",<br>"titles": "titles",<br>"contents": "contents1",<br>"passwords": 123456}                                                                                                                                                               |
| DELETE | /posts/{id} | {"passwords":123456}                                                                      | {"success": true}                                                                                                                                                                                                                                            |

## 수정, 삭제 API의 request 사용 방식
 - param방식의 PathVariable을 사용하였음.<br>
   수정이나 삭제의 기능은 id와 매칭되는 데이터 하나를 가져오면 되는 것이라 PathVariable을 사용함.<br>
   만일 1~10번까지의 id를 뽑아내야 하는 경우였다면 query방식을 사용하였을 것이다.

## 관심사 분리
 - controller, service, repository의 각 클래스간 이동시에 dto를 사용하여 의존성을 낮출 수 있도록 하였음.<br>


## RESTful을 위해
 - http 메소드를 각 기능에 맞게 사용하고자 하였음.
 - uri에 어떤 작업을 수행하는지 동작을 작성하지 않고, 명사로만 작성하였음.
 - 오류 방지를 위해 url 마지막에 슬래쉬를 포함시키지 않음.
 - 브라우저에서의 문제 방지를 위해 언더스코어를 구분자로 활용하지 않음.
