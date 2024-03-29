# Chapter06. 라우팅
## 6-1 라우팅: 외부 네트워크로 데이터를 보낸다

이더넷과 무선 LAN으로 같은 네트워크 안에서 데이터를 전송할 수 있다.

그러나 다른 네트워크에 데이터를 보내려면 네트워크를 서로 연결하는 라우터로 전송해야 한다.

<br/>

### 라우팅

> 연결된 네트워크의 라우터로 데이터를 전송하는 것

- 라우터는 데이터의 목적지가 어느 네트워크에 접속해 있는지 판단하고, 다음 라우터(= 넥스트 홉)로 데이터를 전송한다.

  → 라우팅을 반복하면 멀리 떨어진 네트워크에도 데이터를 전송할 수 있다.

- 라우터의 전송 대상 데이터는 IP 패킷

  → IP 패킷은 인터넷층에 속하므로 라우팅도 인터넷층에서 일어난다.

- 라우터가 IP 패킷을 전송할 때는 IP 헤더 안에 목적지 IP 주소를 확인한다.
- IP 헤더의 TTL과 헤더 체크섬만 변경된 채로 전송된다.
    - NAT 주소 변환이 이루어질 때는 IP 주소도 변경된다.
    - 이더넷 헤더 등 네트워크 인터페이스층 프로토콜 헤더는 라우터가 전송할 때 완전히 새로운 헤더로 교체된다.

      → 다른 라우터까지 보내기 위해 필요한 정보기 때문

<br/><br/>

## 6-2 네트워크의 연결: 라우터에서 네트워크 연결에 필요한 주소 설정

라우터로 네트워크에 접속하기 위해서는 라우터 인터페이스에 물리적인 배선을 하고, IP 주소를 설정해야 한다.

라우터에 여러 개의 인터페이스가 준비되어 있으므로, 물리적인 배선과 IP 주소 설정을 각각 할 필요가 있다.

<br/><br/>

## 6-3~6 라우터의 데이터 전송 흐름

(호스트1 ↔ 라우터1) ↔ (라우터2 ↔ 호스트2) 구성에서 호스트1 → 호스트2 로 데이터를 전송한다고 했을 때

1. 라우팅 대상인 IP 패킷을 수신한다
    - (호스트1 → 라우터1) 의 IP 패킷 정보
        - 목적지 레이어2 주소(MAC 주소): 라우터1의 MAC 주소
        - 목적지 IP 주소: 호스트2의 IP 주소

<br/>

2. 경로 정보를 검색해서 전송할 곳을 결정한다

   → 목적지 IP 주소로 라우팅 테이블 상의 경로 정보를 검색해 전송할 곳을 결정

    - 라우터1은 목적지 IP 주소와 일치하는 라우팅 테이블의 경로 정보를 검색
        - 라우터2를 넥스트 홉으로 지정

<br/>

3. 레이어2 헤더를 수정해 IP 패킷을 전송한다.
    - 라우터1과 라우터2는 이더넷으로 연결된 네트워크 구성이기 때문에 라우터2로 전송하기 위해서는 이더넷 헤더를 추가해야 한다.

      → 라우터2의 MAC 주소 필요

    - ARP를 실행하여 라우터2의 MAC 주소를 알아낸다.

      → 새로운 이더넷 헤더로 바꾼 후(+ FCS 추가) IP 패킷을 라우터2로 전송

    - IP 헤더의 IP 주소는 달라지지 않지만 TTL을 -1하고, 헤더 체크섬을 다시 계산한다.
    - (라우터1 → 라우터2) 의 IP 패킷 정보
        - 목적지 레이어2 주소(MAC 주소): 라우터1과 연결된 라우터2 인터페이스 주소
        - 목적지 IP 주소: 호스트2의 IP 주소

<br/>

4. 라우터2에서도 라우팅1처럼 처리를 한다.
    - 라우터2도 라우팅 테이블에서 목적지 IP 주소와 일치하는 경로 정보를 탐색한다.

      → 호스트2가 같은 네트워크 내에 있는 것을 확인

<br/>

5. 라우터2 → 호스트2로 전송한다.
    - 호스트2로 IP 패킷을 전송하기 위해 ARP를 실행하여 호스트2의 MAC 주소를 알아낸다.

      → 새로운 이더넷 헤더로 바꾼 후 IP 패킷을 호스트2로 전송

<br/>

6. 요청이 전송된 방향과 반대 방향으로 응답을 전송한다.

<br/><br/>

## 6-7 라우팅 테이블: 라우터가 인식하는 네트워크 정보

### 라우팅 테이블

- 경로(루트) 정보가 등록되어 있다.
    - 경로(루트) 정보

      : 특정 네트워크로 IP 패킷을 전송하려면 다음에 어느 라우터로 전송해야 하는지에 대한 경로 정보

- 경로 정보에 기재되는 내용은 제품에 따라 조금씩 차이가 있지만 가장 중요한 것은 목적지의 네트워크 주소/서브넷마스크와 넥스트 홉 주소

<br/>

라우터는 라우팅 테이블로 이웃 라이터의 네트워크 구성을 인식한다.

네트워크의 자세한 구성이 아니라 이웃 라우터 저 너머에 어떤 네트워크가 있는가 정도로만 인식한다.

→ 연결된 네트워크로 전송을 반복해 가는 것이므로 이웃 라우터까지나 전송할 수 있으면 되기 때문

<br/>

라우팅 테이블 상에서 인식할 수 없는 네트워크로 가는 IP 패킷은 모두 폐기된다.

따라서, 라우팅 테이블에 필요한 경로 정보를 모두 등록해야 한다.

<br/><br/>

## 6-8~9 라우팅 테이블을 만드는 방법

라우팅 테이블에 경로 정보를 등록하려면,

- 직접 접속
- 스태틱 라우팅
- 라우팅 프로토콜

<br/>

### 방법 1) 직접 접속

- 직접 접속의 경로 정보는 라우터가 직접 연결된 네트워크의 경로 정보로 가장 기본적인 경로 정보이다.
- 인터페이스에 IP 주소를 설정하면 자동으로 직접 접속된 경로 정보가 라우팅 테이블에 등록된다.

  → 특별한 설정을 하지 않아도 바로 직접 접속된 네트워크 사이에서 라우팅 가능

<br/>

라우터에 직접 접속되지 않은 원격 네트워크의 경로 정보를 라우팅 테이블에 등록하는 방법은 다음과 같다.

### 방법 2) 스태틱 라우팅

- 라우터에 네트워크 주소/서브넷 마스크와 넥스트 홉 주소를 수동으로 커맨드를 입력해서 등록한다.

<br/>

### 방법 3) 라우팅 프로토콜

- 라우터에서 라우팅 프로토콜을 활성화하면, 라우터끼리 정보를 교환해 라우팅 테이블에 필요한 경로 정보를 등록해 준다.
- 종류
    - RIP
    - OSPF
    - BGP

<br/><br/>

## 6-10~11 경로 정보를 효율적으로 등록하는 방법

### 경로 요약

모든 네트워트의 경로 정보를 일일이 라우팅 테이블에 등록하기는 어렵다.

또한 라우팅 동작은 넥스트 홉까지만 보내면 되므로, 넥스트 홉이 같은 네트워크까지 라우팅 테이블에 등록하는 것은 의미가 별로 없다.

→ 경로 요약 고안

<br/>

경로 요약으로 공통의 넥스트 홉을 가진 여러 네트워크 주소를 하나로 모아 라우팅 테이블에 등록할 수 있다.

경로 요약으로 라우팅 테이블을 깔끔하게 정리할 수 있다.

스태틱 라우팅으로 설정할 때 경로 요약을 하면, 설정하는 항목을 줄일 수 있다.

라우팅 프로토콜이면 라우터끼리 주고받는 정보를 줄여서 네트워크 비용을 줄일 수 있다.

<br/>

### 디폴트 경로

> ‘0.0.0.0/0’ 으로 나타내는 경로 정보

- 모든 네트워크를 집약
- 경로 요약을 가장 극단적으로 적용

<br/>

디폴트 경로를 라우팅 테이블에 등록해 두면, 모든 네트워크의 경로 정보를 등록한 게 된다.

그래서 보통 인터넷으로 패킷을 라우팅하기 위해 디폴트 경로를 등록하는 경우가 많다.

<br/><br/>

## 6-12 레이어3 스위치: 라우터와 레이어2 스위치의 기능을 가진 데이터 전송 기기

### 레이어3 스위치

> 레이어2 스위치에 라우터 기능을 추가한 네트워크 기기

- 같은 네트워크로 데이터를 전송할 때는 레이어2 스위치처럼 MAC 주소 기반으로 전송
- 다른 네트워크로 데이터를 전송할 때는 라우터처럼 IP 주소를 기반으로 전송

→ VLAN(Virtual LAN) 기능 이용

<br/><br/>

## 6-13~14 VLAN: 레이어2 스위치로 네트워크를 분할한다

하나의 네트워크에 수많은 기기를 연결하면 불필요한 데이터 전송이 많이 발생한다.

불필요한 데이터 전송을 줄이고, 보안이나 관리면에서 네트워크 분할이 필요하다.

<br/>

### VLAN

VLAN으로 레이어2 스위치에서 가상으로 네트워크를 분할할 수 있다.

- 동작 원리
    - 일반 레이어2 스위치는 모든 포트 사이에서 이더넷 프레임을 전송할 수 있다.
    - 그것을 VLAN으로 나누어 같은 VLAN에 할당한 포트끼리만 이더넷 프레임을 전송하도록 한다.

      → VLAN이 다르면 이더넷 프레임을 전송할 수 없음

<br/>

- 장점
    - VLAN 별 스위치 사이는 연결되어 있지 않으므로, 네트워크를 분할하여 VLAN 간의 데이터를 분리한다.

      → 데이터가 전송되는 범위가 제한되므로 보안 향상

<br/>

라우터로도 네트워크를 분할하지만, 라우터로 분할된 네트워크는 라우터로 서로 연결되어 있다.

반면 VLAN으로 분할한 네트워크 간에는 통신할 수 없다.

따라서, 라우터와 레이어3 스위치로 VLAN으로 분할된 네트워크를 서로 연결하여 통신할 수 있게 한다.

→ VLAN 간 라우팅

<br/><br/>

## 6-15 태그 VLAN, IEEE802.1Q: 복수의 접속선을 한 줄로 깔끔하게 정리한다

VLAN은 복수의 스위치에 걸쳐서 만들 수도 있다.

(단, VLAN마다 스위치를 연결해줘야 함)

이때 스위치 사이를 효율적으로 연결하기 위해 태그 VLAN 포트가 있다.

<br/>

### 태그 VLAN

> 복수의 VLAN에 할당되어 복수의 VLAN 이더넷 프레임을 전송할 수 있는 포트

→ 복수의 레이어2 스위치로 VLAN을 구성할 때 스위치 간의 연결을 하나로 해결할 수 있다.

<br/>

태그 VLAN 포트로 송수신하는 이더넷 프레임에는 VLAN 태그가 추가된다.

VLAN 태그로 스위치 사이에 전송되는 이더넷 프레임이 원래 어느 VLAN의 이더넷 프레임인지 알 수 있다.

레이어2 스위치는 VLAN 태그로 VLAN을 식별하여 동일 VLAN 포트끼리 이더넷 프레임을 전송한다.

<br/><br/>

## 6-16 VLAN과 태그 VLAN
: 기기 추가나 배선을 변경하지 않고 네트워크를 바꾼다

태그 VLAN 포트는 하나의 포트를 VLAN 별로 가상으로 분할해서 사용할 수 있다.

VLAN은 설정에 따라 네트워크를 어떻게 분할할지 자유롭게 결정할 수 있다.

→ VLAN을 이용하면 네트워크를 유연하게 구성할 수 있다.

<br/><br/>

## 6-17 VLAN 간 라우팅: 분할한 네트워크끼리 연결하는 방법

레이어2 스위치의 VLAN은 네트워크를 분할하기만 한다.

따라서 VLAN 간 통신을 하고싶다면 VLAN을 서로 연결해야 한다.

<br/>

### VLAN 간 라우팅

> VLAN을 서로 연결해서 VLAN끼리 통신할 수 있게 하는 것

VLAN 간 라우팅을 위해서는 라우터나 레이어3 스위치가 필요하다.

(라우터보다는 레이어3 스위치를 이용하는 것이 효율적)

<br/>

레이어3 스위치로 네트워크(VLAN)를 연결하려면, 레이어3 스위치에 에 IP 주소를 설정해야 한다.

- IP 주소 설정 방법
    - 레이어3 스위치 내부의 가상 인터페이스(VLAN 인터페이스)에 IP 주소를 설정한다.
    - 레이어3 스위치의 포트 자체에 IP 주소를 설정한다.

<br/><br/>

## 6-18 기본 게이트웨이: PC에도 라우팅 테이블이 있다

PC나 서버 등 TCP/IP를 이용하는 모든 기기에는 라우팅 테이블이 있고, 라우팅 테이블에 따라 IP 패킷을 전송한다.

PC도 라우터처럼 라우팅 테이블에 기재되지 않은 네트워크로는 IP 패킷을 보낼 수 없다.

PC의 라우팅 테이블에 IP 패킷을 전송하고 싶은 모든 네트워크를 등록해야 한다.

<br/><br/>

그러나 PC의 라우팅 테이블에는 너무 자세히 경로 정보를 등록하지 않는다.

보통 다음 두 가지의 경로만 등록한다.

- 직접 접속 경로 정보: IP 주소 설정
- 디폴트 경로: 기본 게이트웨이의 IP 주소 설정
    - PC 자신이 연결되어 있는 네트워크 이외는 전부 0.0.0.0/0 인 디폴트 경로로 모아서 등록