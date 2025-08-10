import http from 'k6/http';

// 옵션 정의
export const options = {
  vus: 100,
  iterations: 1000,
};

// 실제 호출 함수
export default function () {
  http.batch([
    ['POST', 'http://localhost:8080/variable/decrease'],
    // ['POST', 'http://localhost:8081/variable/decrease'],

    // ['POST', 'http://localhost:8080/variable/sync-decrease'],
    // ['POST', 'http://localhost:8081/variable/sync-decrease'],
    //
    // ['POST', 'http://localhost:8080/variable/lock-decrease'],
    // ['POST', 'http://localhost:8081/variable/lock-decrease'],
  ]);
}
