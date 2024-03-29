# Chapter05. 이더넷과 무선 LAN
## 5-1 이더넷, 무선 LAN(WI-FI): 같은 네트워크 내에서의 전송을 반복한다

기술적인 관점에서, ‘네트워크’는 라우터 또는 레이어3 스위치로 구획되는 범위다.

네트워크의 기본적인 구성은 레이어2 스위치로 하나의 네트워크를 구성하고, 라우터 또는 레이어3 스위치로 각 네트워크를 서로 연결하는 것이다.

<br/>

### 같은 네트워크 내의 전송을 반복해 간다

다른 네트워크에 접속된 서버까지의 데이터 전송은 같은 네트워크 내의 전송을 반복해감으로써 실현한다.

![스크린샷 2023-03-23 오전 1.01.17.png](./image/img_06.png)

PC에서 서버로 가는 데이터는 우선 PC와 같은 네트워크 상에 있는 라우터로 전송한다.

라우터는 다시 같은 네트워크 상의 다음 라우터로 전송한다.

이 과정을 반복하여 데이터가 목적지 네트워크 상의 라우터까지 도달하면 그 라우터가 서버로 데이터를 전송한다.

<br/>

이때 같은 네트워크 내에서 전송하는 프로토콜로 자주 이용하는 것이 네트워크 인터페이스층에 속한 이더넷과 무선 LAN(WI-FI)이다.

<br/><br/>

## 5-2 이더넷의 개요: 데이터를 전송하는 이더넷

### 이더넷

- TCP/IP의 네트워크 인터페이스층에 있다.
- 같은 네트워크 내의 이더넷 인터페이스 사이에서 데이터를 전송한다.
    - 같은 네트워크 → 같은 레이어2 스위치에 연결

  (레이어2 스위치는 이더넷으로 전송하는 데이터에 변경을 가하지 않는다.)

<br/>

이더넷 인터페이스가 있는 기기끼리 연결해서 이더넷 링크를 만들면 유선 네트워크가 된다.

<br/><br/>

## 5-3 IEEE802 위원회: 이더넷의 규격

이더넷의 규격은 IEEE802 위원회에서 결정된다.

이더넷의 규격은 주로 최대 전송 속도와 이용하는 매체(케이블)에 따라 나뉜다.

<br/>

### 이더넷 규격의 명칭

- 종류
    1. IEEE802.3 으로 시작되는 이름
    2. 전송 속도와 전송 매체의 특징을 조합한 이름
        - ex) 1000BASE-T
            - `1000` : 전송 속도(기본적으로 Mbps 단위)
            - `BASE` : 베이스 밴드 방식
            - `- 뒤` : 전송 매체나 물리 신호 변환의 특징

<br/><br/>

## 5-4 MAC 주소: 인터페이스는 어느 것?

### MAC 주소

> 이더넷 인터페이스를 특정하기 위한 48비트 주소

- 전반 24비트의 OUI와 후반 24비트의 시리얼 넘버로 구성
    - OUI: 이더넷 인터페이스를 제조하는 벤더 식별 코드
- 16진수로 표기

  ex) 00-00-01-02-03-04, 00:00:01:02:03:04, 0000.0102.0304, …

- 이더넷 인터페이스 간 데이터를 전송하기 위해 필요
- 이더넷 인터페이스에 미리 할당 되어 있어 변경할 수 없다.

<br/><br/>

## 5-5 RJ-45 인터페이스와 UTP 케이블: 일반적으로 사용되는 인터페이스와 케이블은?

### 자주 사용하는 이더넷 규격

- UTP 케이블
    - 흔히 말하는 LAN 케이블
    - 케이블의 품질에 따라 카테고리가 나뉜다.
    - 카테고리에 따라 지원할 수 있는 주파수가 달라지고, 각각 용도나 전송 속도가 정해진다.
- RJ-45 이더넷 인터페이스

<br/><br/>

## 5-6 이더넷의 프레임 형식: 데이터 형식

### 이더넷 프레임

> 전송할 데이터 + 이더넷 헤더 + FCS

**이더넷 헤더:**

→ 어느 인터페이스에서 어느 인터페이스로 전송하는 데이터인지 MAC 주소로 지정

- 목적지 MAC 주소
- 출발지 MAC 주소
- 타입 코드: 이더넷으로 운반할 대상의 데이터
    - ex) 0x0800 - Ipv4, 0x86DD - IPv6

<br/>

**FCS:**

- 에러 체크 담당

<br/>

이더넷으로 전송할 데이터는 64바이트 ~ 1500바이트 사이로 정해져 있다.

MTU를 넘는 크기의 데이터는 분할해서 전송한다.

- MTU(Maximum Transmission Unit): 데이터 크기의 최댓값

<br/><br/>

## 5-7 토폴로지: 어떻게 접속하는가?

### 토폴로지

> 네트워크의 연결 형태

- 주요 형태
    - 버스형
        - 하나의 전송 매체(동축 케이블)를 복수의 기기가 공유
        - 이더넷은 전송 메체를 어떻게 공유할 것인지를 제어하기 위해 CSMA/CD 방식을 이용
    - 스타형
        - 레이어2 스위치가 중심
    - 링형

<br/><br/>

## 5-8 CSMA/CD: 데이터 전송 타이밍 제어

초기의 버스형 토폴로지로 된 이더넷에서는 복수의 기기가 동시에 데이터를 전송할 수 없다.

단 한 대의 기기만 특정 시점에 데이터를 전송할 수 있다.

전기 신호로서 데이터를 보낼 수 있는 회로는 하나이기 때문이다.

<br/>

### CSMA/CD

> 빠른 쪽이 이기는 제어 방식의 이더넷 전송 매체 공유 메커니즘

**흐름:**

1. CS를 통해 케이블이 현재 사용 중인지 확인
    - 케이블이 사용중이면 대기
2. 케이블이 비어 있으면 데이터 전송
3. 동시에 여러 호스트가 케이블이 비었다고 판단해서 여러 곳에서 데이터가 전송된 경우 충돌 발생

   → 데이터는 파괴되어 버림

4. 충돌이 발생하면 랜덤한 시간동안 대기한 후 데이터를 다시 전송

   → 같은 타이밍에 데이터를 보내면 다시 충돌할 가능성이 높기 때문


그러나 현재 이더넷은 전송 매체를 공유하지 않으므로 CSMA/CD가 필요없다.

<br/><br/>

## 5-9 레이어2 스위치: 이더넷 네트워크를 만든다

### 레이어2 스위치

> 이더넷을 이용한 네트워크 하나를 구성하는 네트워크 기기

→ 레이어2 스위치를 여러 대 연결해도 하나의 네트워크

- 레이어2 스위치로 구성된 한 이더넷 네트워크 안에서 MAC 주소에 기반해 데이터(이더넷 프레임)를 전송
- 수신한 이더넷 프레임에 변경 작업 없이 그대로 전송

  (이더넷 헤더의 MAC 주소만 확인)

- 하나의 네트워크에 접속하려면 레이어2 스위치에 접속해야 하기 때문에 네트워크의 입구 역할도 한다고 볼 수 있다.

<br/><br/>

## 5-10~13 레이어2 스위치의 데이터 전송: 레이어2 스위치의 동작1~4

레이어2 스위치가 데이터를 전송하기 위해 필요한 설정은 따로 없다.

**레이어2 스위치의 데이터 전송 동작 흐름:**

1. 수신한 이더넷 프레임의 출발지 MAC 주소를 MAC 주소 테이블에 등록한다.
2. 목적지 MAC 주소와 MAC 주소 테이블에서 전송할 포트를 결정
3. 이더넷 프레임 전송

<br/>

- 플러딩

  : MAC 주소가 MAC 주소 테이블에 존재하지 않으면 수신 포트를 제외한 모든 포트로 전송하는 것

    - 레이어2 스위치의 전송 범위는 같은 네트워크 한정이므로 큰 영향은 없다.
    - 수신한 이더넷 프레임은 하나기 때문에 플러딩을 하려면 포트 수만큼 데이터를 복제해야 한다.
    - 수신한 이더넷 프레임의 목적지 MAC 주소와 데이터를 받은 호스트의 MAC 주소가 다르면 해당 호스트는 전송된 이더넷 프레임을 파기
    - 한 번의 요청을 플러딩으로 하게 되면 응답을 보낼 때에는 이미 각 MAC 주소 테이블에 요청 출발지 MAC 주소가 등록되어 있으므로 플러딩을 할 필요가 없어진다.
- Unknown 유니캐스트 프레임

  : MAC 주소 테이블에 등록되지 않은 MAC 주소가 목적지인 이더넷 프레임

<br/>

레이어2 스위치는 MAC 주소를 MAC 주소 테이블에 계속 등록하면서 같은 네트워크 내의 이더넷 인터페이스로 이더넷 프레임을 전송해 간다.

복수의 레이어2 스위치가 있어도, 각각의 레이어2 스위치는 같은 방식으로 동작한다.

<br/><br/>

## 5-14 MAC 주소 테이블: MAC 주소 테이블 관리

여러 대의 스위치를 연결한 경우에는 하나의 포트에 여러 개의 MAC 주소가 등록될 수 있다.

- ex) SW1, SW2이 서로 3번 포트로 연결되어 있고, SW1은 호스트 A와 호스트 B, SW2는 호스트 C와 호스트 D가 각각 연결되어 있다고 할 때
    - SW1 입장에서는 호스트 C와 호스트 D가 똑같이 3번 포트로 연결되었다고 본다.
    - SW2도 마찬가지로 호스트 A와 호스트 B가 3번 포트로 연결되었다고 본다.

<br/>

MAC 주소 테이블에 등록하는 MAC 주소 정보에는 제한 시간이 설정되어 있다.

제한 시간은 스위치 기기에 따라 다르지만 보통 5분 정도이다.

등록된 MAC 주소가 출발지로 되어 있는 데이터를 수신하면 제한 시간이 다시 리셋된다.

<br/><br/>

## 5-15 전이중 통신: 데이터 전송과 동시에 수신한다

### 전이중 통신

> 데이터 송신과 수신을 동시에 하는 것

- cf) 반이중 통신
    - 송신과 수신을 동시에는 할 수 없어 서로 전환하면서 처리하는 것

초기 이더넷은 전송 매체를 공유하는 버스형 토폴로지였기 때문에 특정 시간에는 한 대만 데이터를 전송할 수 있고 나머지는 수신만 할 수 있다.

현재 레이어2 스위치를 기반으로 만든 이더넷 네트워크에서는 전이중 통신이 가능하다.

- 데이터 수신용과 송신용으로 전송 매체를 나누어 사용

<br/><br/>

## 5-16 무선 LAN: 케이블 없이 간편하게 네트워크를 만든다

### 무선 LAN

> 케이블 없이도 간편하게 LAN을 구축할 수 있는 LAN 기술

- 무선 LAN을 만들기 위해서는 무선 LAN 액세스 포인트와 무선 LAN 인터페이스가 필요하다.

  (무선 LAN 인터페이스는 대부분 노트북, 스마트폰, 태블릿 등 기기에 미리 내장되어 있다.)

- 무선 LAN의 데이터 통신은 무선 LAN 액세스 포인트를 경유한다.
    - 인프라스트럭처 모드: 무선 LAN 액세스 포인트를 경유해 데이터를 주고 받는 것
- 무선 LAN 클라이언트의 애플리케이션에서 요청을 보낼 목적지 서버는 거의 유선 이더넷을 이용한다.
    - 무선 LAN 클라이언트: 무선 LAN 인터페이스로 무선 LAN에 연결된 기기

  → 무선 LAN만으로는 통신이 완결되지 않는 경우가 많다.

  → 따라서, 무선 LAN 액세스 포인트는 레이어2 스위치와 접속해 유선 이더넷 네트워크와도 연결된다.

<br/><br/>

## 5-17 IEEE802.11b/a/g/n/ac: 무선 LAN에도 규격이 많다.

무선 LAN 규격의 커다란 차이는 이용하는 전파의 주파수 대역이다.

크게 2.4GHz 대역과 5GHz 대역의 주파수를 이용하는 규격으로 나뉜다.

또한 데이터를 변환해서 어떻게 전파에 실어보내느냐에 따라 전송 속도도 바뀐다.

<br/>

### Wi-Fi

원래 무선 LAN 기기 간 상호 접속을 보중하고자 사용됐지만, 현재는 무선 LAN 자체를 가리키는 용어가 됐다.

(예전에는 무선 LAN 기기끼리 호환이 잘 되지 않아, 제조사가 다르면 원활하게 접속할 수 없는 경우도 많았기 때문)

<br/><br/>

## 5-18 어소시에이션: 무선 LAN에 연결한다.

### 어소시에이션

> 무선 LAN에 연결하는 것(= 유선 이더넷의 케이블 배선)

- 무선 LAN으로 통신하려면 무선 LAN 액세스 포인트 어소시에이션한다.
- 어소시에이션에는 SSID가 필요
    - SSID

      > 무선 LAN의 논리적인 그룹을 식별하는 식별정보

        - 미리 무선 LAN 액세스 포인트에는 최대 32문자의 문자열로 지정해둔다.
        - 한 대의 엑세스 포인트에 복수의 SSID를 설정할 수도 있고, 복수의 액세스 포인트에 대해 같은 SSID를 설정할 수도 있다.
        - 암호화나 인증 등의 보안 설정은 SSID 별로 한다.

**흐름:**

1. 액세스 포인트의 제어 신호(비콘)에서 이용 가능한 주파수 대역(채널)을 검출
2. SSID를 지정해서 액세스 포인트에 어소시에이션 요청
    - 액세스 포인트는 어소시에이션 응답으로 접속 가부를 통지 받음

<br/><br/>

## 5-19 통신속도: 전파는 돌려쓴다

### 무선 LAN의 규격상 통신 속도

무선 LAN은 규격에서 정한 속도대로 통신할 수 있는 경우가 거의 없다.

무선 LAN은 전파를 돌려쓰기 때문에 무선 LAN의 스루풋은 규격상 전송속도의 절반 정도 밖에 되지 않는다.

(초기 이더넷에서 하나의 전송매체를 돌려쓰는 것과 같음)

<br/>

### 무선 LAN의 충돌

무선 LAN 액세스 포인트에 복수의 클라이언트가 어소시에이션하고 있으면, 복수의 클라이언트들이 전파를 공유해서 이용한다.

어느 시점에 무선 LAN에서 데이터를 전파에 실어 송신할 수 있는 것은 하나의 무선 LAN 클라이언트 뿐이다.

만약 동시에 여러 클라이언트에서 데이터를 전파에 실어 보내버리면, 전파가 중첩되어 충돌이 발생한다.

→ 수신하는 쪽에서도 원래 데이터를 재구성할 수 없게 된다.

따라서, 무선 LAN에서는 어떤 시점에 어떤 클라이언트가 데이터를 전파에 실어 송신할지 제어가 필요하기 때문에 CSMA/CA를 이용한다.

<br/><br/>

## 5-20 CSMA/CA: 충돌이 일어나지 않게 데이터를 전송한다

### CSMA/CA

> 빠른 쪽이 이기는 방식으로 전파를 이용하는 메커니즘

**흐름:**

1. 데이터를 전송하려고 할 때, 전파가 이용 중인지 확인
    - 엑세스 포인트에 어소시에이션 할 때 확인한 채널에서 전파를 검출
        - 채널: 무선 LAN 액세스 포인트에서 설정한 특정 주파수대의 전파
    - 검출된 전파가 이용 중일 때는 대기
    - 전파가 검출되지 않았다면 일정 시간 대기

1. 랜덤 시간 대기
    - 전파가 이용 중이 아니면 데이터를 송신할 수 있지만, 바로 송신하지 않고 랜덤 시간 동안 대기

      → 복수의 클라이언트가 동시에 전파를 미사용으로 판단하여 바로 데이터를 송신하면 충돌이 발생할 수 있기 때문

    - 각각의 클라이언트가 랜덤한 시간을 대기함으로써 다른 클라이언트와 송신 타이밍을 어긋나게 함

1. 데이터 송신
    - 무선 LAN 통신에서는 데이터를 수신했으면 수신 확인 응답으로 ACK를 반환

이런 방식으로 계속해서 제어하기 때문에 무선 LAN 클라이언트가 데이터를 보내려고 할 때 대기 시간이 길어지고 스루풋이 저하된다.

<br/><br/>

## 5-21 WPA2: 무선 LAN의 보안

### 무선 LAN 보안의 핵심

무선 LAN이 간편하고 편리하게 사용할 수 있는 만큼, 악의를 가진 사용자도 마찬가지다.

따라서 무선 LAN 보안을 제대로 확보하는 것이 중요하다.

<br/>

무선 LAN 보안의 핵심은 데이터 암호화와 사용자 인증이다.

사용자 인증을 통해 무선 LAN 액세스 포인트에 정식 사용자만 접속할 수 있게 한다.

무선 LAN으로 송수신하는 데이터를 암호화하여 전파 도청을 방지한다.

<br/>

### WPA2

- 무선 LAN의 보안 규격
- 데이터 암호화에 AES, 인증에 IEEE802.1X를 이용