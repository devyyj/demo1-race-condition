import http from 'k6/http';

export let options = {
  scenarios: {
    stress_app1_app2: {
      executor: 'per-vu-iterations',
      vus: 100,
      iterations: 50,
      maxDuration: '2m',
    },
  },
};

export default function () {
  http.batch([
    // 낙관락
    // ['POST', 'http://localhost:8080/counter/decrease'],
    // ['POST', 'http://localhost:8081/counter/decrease'],
    // 비관락
    // ['POST', 'http://localhost:8080/stock/decrease'],
    // ['POST', 'http://localhost:8081/stock/decrease'],
    // 분산락
    ['POST', 'http://localhost:8080/stock/redis-decrease'],
    ['POST', 'http://localhost:8081/stock/redis-decrease'],
  ]);
}