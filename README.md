### **1\. 최종 코드**

#### 1.1. 버전

1.1.1. \[ ver1 \] 20231016(월) : 발표

[https://github.com/ohsukyoung/salad\_jo\_ver1](https://github.com/ohsukyoung/salad_jo_ver1)  
 

1.1.2. **\[현재최종버전\]** \[ ver2 \] 20231022(일) : 피드백 수정 및 리펙토링

[https://github.com/ohsukyoung/salad\_jo\_ver2](https://github.com/ohsukyoung/salad_jo_ver2)

 [GitHub - ohsukyoung/salad\_jo\_ver2

Contribute to ohsukyoung/salad\_jo\_ver2 development by creating an account on GitHub.

github.com](https://github.com/ohsukyoung/salad_jo_ver2)

#### 1.2. 버전별 코드 안내

| **버전표기** | **날짜** | **구분** |
| --- | --- | --- |
| ver1 | 2023.10.16(월) | **최초생성**   \- NEW(최초) 원하는 대로 만들어 먹는 샐러드 키오스크 |
| ver2 | 2023.10.22(일) | **관리자>재고관리 기능 제공**   \- FEATURE(기능) 관리자 > 재고관리   ㄴ 기존 기획 버전을 롤백하여 대메뉴로 기능 제공      **관리자>판매관리 상세수정으로 기능변경 및 판매관리 사용자 연동**   \- CHANGED(변경) 관리자 > 판매관리   ㄴ 기존 기획과 싱크를 맞추기 위해 판매관리의 기능을 재고관리, 사장추천 > 상세 수정에 포함      **관리자 재료와 사장추천 연동**   \- FIXED(수정) 관리자> 사장추천   ㄴ 재료리스트 수정시 사장추천 개수 연동 될 수 있도록 수정 |

#### 1.3. 구현 영상

1.3.1. 버전1 영상

<iframe src="https://play-tv.kakao.com/embed/player/cliplink/441958273?service=daum_tistory" width="860" height="513" frameborder="0" allowfullscreen="true"></iframe>

\[버전1\] 샐러드먹조 실행 영상

1.3.2. 버전2 영상

<iframe src="https://play-tv.kakao.com/embed/player/cliplink/441984212?service=daum_tistory" width="860" height="513" frameborder="0" allowfullscreen="true"></iframe>

\[버전2\] 샐러드먹조 실행  영상

---

[##_Image|kage@bkdjv4/btsyUg3jENA/j912WNxI4koqihwhX3kxP0/img.png|CDM|1.3|{"originWidth":1905,"originHeight":1069,"style":"alignCenter","width":500,"height":281}_##]

### **2\. 프로젝트 개요**

#### 2.1. 프로젝트 소개 및 진행과정

1.  프로젝트 소개
    -   기간: 2023.09.25 ~ 2023.10.16
    -   조건: Vending Machine / 언어사용은 Java만으로, DB 사용X
    -   주제: 샐러드 매장 키오스크
2.  프로젝트 진행과정

| **NO** | **Content** | **Start**   _(\* End 없을 경우   하루 진행)_ | **End** |
| --- | --- | --- | --- |
| 1\. 시작 | \- 역할분배 | 2023-09-25 |   |
| 2. 기획 | \- 주제 선정   \- 플로우 차트 설계   \- 기획안 작성   \- 파트분배 | 2023-09-25 | 10-02 |
| 3. 기능 구현 | \- 각 파트 구현 후 피드백   \- 피드백 수정 | 2023-10-04 | 10-09 |
| 4. 기능 통합 | \- 기능 통합 중 각 파트 부족한 기능 수정  | 2023-10-10 | 10-12 |
| 5. 프로토타입 | \- 기능 통합 중 오류 추출 및 해결 | 2023-10-13 |   |
| 6. 검토 & 수정 | \- 코드 최종 수정   \- 최종보고서 작성   \- ppt 발표 준비 | 2023-10-13 | 10-15 |
| 7\. 발표(ver1) | \- 발표 & QnA & 피드백 | 2023-10-16 |   |
| 8\. 보완(ver2) | \- ver1 수정 및 보완 | 2023-10-19 | 10.22 |

#### 2.2. 프로젝트 기획

1.  주제 선정 이유
    1.  발전 가능성이 높은 모델  
        ㄴ 코로나 이후로 혼밥인원이 늘었고, 샐러드 찾는 소비자가 많아짐
    2.  시도하고 싶은 기능을 포괄할 수 있는 주제(ex. 메뉴선택, 결재, 회원/비회원 등)
2.  목적
    1.  사용자가 즉시 칼로리를 확인하며 자신이 원하는 샐러드를 조합하여 구매가 가능하도록 하고 선택이 어려운 사용자의 경우 관리자가 추천하는 조합을 이용하는 키오스크 구현
    2.  관리자가 재료관리, 추천조합 관리, 회원관리, 매출관리 를 통해 전반적인 통제가 가능한 키오스크 구현

#### 2.3. 프로젝트 설계

1.  관리자 워크플로우 차트  
    [##_Image|kage@GSvkc/btsyTEXF5Fq/GGLTvV6uwr2akjwfMllGU0/img.png|CDM|1.3|{"originWidth":1577,"originHeight":1118,"style":"alignCenter","caption":"관리자 워크플로우"}_##]
2.  사용자 워크플로우 차트

[##_Image|kage@bmsCTZ/btsyXYm3lFp/eKYj42saOxZyztjXMVHoeK/img.png|CDM|1.3|{"originWidth":1581,"originHeight":1119,"style":"alignCenter","caption":"사용자 워크플로우"}_##]

### **3\. 나의 구현**

#### 3.1. 맡은 역할

1.  계획단계 역할: 일정관리
2.  개발주요파트: **사용자 모드의 포장여부**(사장추천, 나만의샐러드, 음료, 사이드) 파트 구현

|   | **(팀원1)** | **(팀원2)** | **(팀원3)** | **나 (팀장)** | **(팀원4)** |
| --- | --- | --- | --- | --- | --- |
| 계획 | 보고서 작성 | 회의록 작성 | 발표 | 일정관리 | 기획서 작성 |
| 개발 | 관리자>매출관리 | 관리자>회원관리 | 관리자>재고관리 | 사용자>메뉴선택 | 사용자>결제 |

#### 3.2. 구현한 기능

1.  \[ ver1 \] (사용자) 관리자 저장 리스트 각 조건에 맞게 출력
2.  \[ ver1 \] (사용자) 사용자 선택 값 저장하여 리스트로 장바구니, 매출 관리에 활용할 수 있도록 전달
3.  \[ ver2 \] (관리자) 판매관리(재고셋팅, 품절관리) 기능 롤백하여 현재 기능에 맞게 조정
4.  \[ ver2 \] (관리자) 사장추천과 재료의 상관관계를 고려하여 재료리스트를 참조함으로써 재료의 현행화

#### 3.3. 마주한 문제 및 극복방법

| 마주한 문제 |  | 극복방법 |
| --- | --- | --- |
| 1\. 재료 리스트   저장 구조 상이 | \- 사용자: 카테고리에 따라 재료리스트 출력   (베이스, 메인토핑, 사이드, 음료 등)   \- 관리자: 하나의 리스트로 관리의 효율 추구 | \- 공통된 하나의 재료 리스트를 사용하고 재료의 저장시 재료의 타입을 enum을 활용해 저장하여 사용자에서 각 타입에 따라 출력 |
| 2\. 재료 관리 구조 | \- 많은 재료에 그에 대한 정보(ex. 가격, 칼로리, 수량 등)를 어떻게 저장 할 것인지 | \- 단순히 값을 묶는 배열보다는 재료의 다양한 속성을 관리하기에 용이   \- 또한, 배열은 고정 데이터 크기이나 관리자가 재료를 동적으로 추가하거나 삭제할 수 있는 컬랙션 선택 |
| 3\. 저장 리스트 공유 | \- 저장된 내용을 공유하기 위한 방법 고민 | \- new 연산자를 통해 메모리를 할당받지 않아도 사용가능하고 공유될 수 있는 static을 사용 |
| 4\. 입력한 내용 프로그램 종료시 유지되지 않음 | \- 프로그램 종료시 기존 저장값이 사라짐 | \- 객체직렬화, 역직렬화를 이용해 DB처럼 사용 |
| 5\. 코드의 중복 | \- 사용자에게 재료타입에 맞는 재료를 출력하거나, 입력받는 코드의 중복 | \- 공통된 요소를 뽑아 매개변수(재료타입)에 따라 출력 될 수 있도록 코드의 효율성을 높임 |
| 6\. 장바구니와 사용자에게 보여지는 재료 리스트 개수 연동 문제 | \- 사용자가 재료를 장바구니에 담았을 경우, 담은 재료를 제외하고 선택할 수 있어야함 | \- 재료리스트 출력이 단순히 재료리스트의 개수를 보여주는 것이 아닌, 장바구니와 연동하여 남은 개수를 노출하여 사용자에게 남은 개수를 인식할 수 있도록 함 |

#### 3.4. 주요 문제 극복 실제 코드

3.4.1. 재료리스트 저장 구조 상이 -> 하나의 재료 리스트 사용, 재료 타입에 따라 출력

3.4.1.1. @before 수정전

```
	/* 1) 카테고리별 재료 리스트 생성 =======================*/
    dep2_infoBase dep2InfoBa = new dep2_infoBase(); // 2dep-베이스 등...
    dep2_infoMain dep2InfoMa = new dep2_infoMain();
    dep2_infoSide dep2InfoSi = new dep2_infoSide();
    dep2_infoSource dep2InfoSo = new dep2_infoSource();
    dep1_infoCeo dep1InfoCe = new dep1_infoCeo();	//사장추천
    dep1_infoDrink dep1InfoDr = new dep1_infoDrink();// 음료
    
(...중략...)
    
    /* 2) 사용자 안내페이지 =======================*/
    public void menuDisp() {
        System.out.println("=============================");
        System.out.println("\t [[샐러드먹조]]");
        System.out.println("\t 1. 사장추천");
        System.out.println("\t 2. 나만의 샐러드");
        System.out.println("\t 3. 음료");
        System.out.println("\t 4. 사이드");
        System.out.println("=============================");
        userSelect = sMenu.menuSelect(4);
    }
    
(...중략...)
    
	/* 3) 카테고리별 static 변수 선언 =======================*/
    static final int E_RCMND 	= 1;   	// 사장추천
    static final int E_MY_SALAD = 2;   	// 나만의 샐러드
    static final int E_DRINK 	= 3;   	// 음료
    static final int E_SIDE 	= 4;   	// 사이드
    
    /* 4) 사용자 선택에 따라 이동 =======================*/
    public void menuRun() {
        switch (userSelect) {
            case E_RCMND    : menuRcmd(); break;
            case E_MY_SALAD : menuMySalad(); break;
            case E_DRINK    : menuDrink(); break;
            case E_SIDE     : menuSide(); break;
            case E_CANCEL   : menuCancel(); break;
        }
    }

(...중략...)

	/* 5) 재료 리스트 출력 =======================*/
    public void menuMySalad() {
        System.out.println("\n2. 나만의 샐러드 -------------------------------------- ");
        dep2InfoBa.menuInfo(mRInner);
        dep2InfoMa.menuInfo(mRInner);
        dep2InfoSi.menuInfo(mRInner);
        dep2InfoSo.menuInfo(mRInner);
    }
```

3.4.1.2. @after 수정후

```
	/* 1) 재료 리스트 생성 =======================*/
    public class CacheData {
        static List<Product> allProductList = new ArrayList<>(); // 제품 리스트
    }
    
(...중략...)
	
    /* 2) 사용자 안내페이지 =======================*/
	public void menuDisp() {
        System.out.println("\n\t[ 사용자 메뉴 선택 ]===========");
        System.out.println("\t1. 사장추천");
        System.out.println("\t2. 나만의 샐러드");
        System.out.println("\t3. 음료");
        System.out.println("\t4. 사이드");
        System.out.println("\t=============================");
    }
    
    /* 3) 제품관련 정보 열거혐(Enum)사용 =======================*/
    enum ProductType {
    //Enum 사용이유: 가독성 및 유지보수, 안전성(enum 타입의 값만 허용), 코드 중복 방지, 특정 값 집합 표현
        RCMND(1, 1, "사장추천"),		// index, depth, name
        MY_SALAD(2, 1, "나만의 샐러드"),
        DRINK(3, 1, "음료"),
        SIDE(4, 1, "사이드"),

        S_BASE(5, 2, "베이스"),
        S_MAIN(6, 2, "메인토핑"),
        S_SIDE(7, 2, "사이드토핑"),
        S_SOURCE(8, 2, "소스"),
	}
    
(...중략...)
    
    /* 4) 사용자 선택에 따라 이동 =======================*/
    public void menuRun(int userSelect) {
        // userSelect를 ProductType으로 변환
        ProductTypeChange productTypeChange = new ProductTypeChange();
        ProductType productType = productTypeChange.ProductTypeChange(userSelect);

        switch (productType) { // DESC: swtich문의 조건에 String 타입을 넣게되면, case문에서 enum타입으로 비교할 수 가 없음
            case RCMND      : menuRcmd();    break;
            case MY_SALAD   : menuMySalad(); break;
            case DRINK      : menuDrink();   break;
            case SIDE       : menuSide();    break;
        }
    }
    
(...중략...)

	/* 4) 프로덕트타입을 넘겨줄시, 해당하는 재료 리스트 출력 =======================*/
    public void menuMySalad() {
        System.out.println("\n\t[ 2. 나만의 샐러드 ]");
        System.out.println("\t[ 베이스 ■ ■ ■ ■ ]");
        info.printInfo(ProductType.S_BASE);
        System.out.println("\t[ ■ 메인토핑 ■ ■ ■ ]");
        info.printInfo(ProductType.S_MAIN);
        System.out.println("\t[ ■ ■ 사이드토핑 ■ ■ ]");
        info.printInfo(ProductType.S_SIDE);
        System.out.println("\t[ ■ ■ ■ 소스 ■ ]");
        info.printInfo(ProductType.S_SOURCE);
    }
    
(...중략...)
	
    /* 5) 프로덕트타입에 따라 재료리스트에서 해당하는 재료 가져옴 =======================*/
    interface PdInterface {
        List<Product> allProductList = CacheData.allProductList;
        List<Product> getList(ProductType productType);
    }

    // 입력받은 ProductType을 기준으로 리스트 생성
    class ProductService implements PdInterface {
        @Override
        public List<Product> getList(ProductType productType) {
            List<Product> result = new ArrayList<>();

            for (Product product : allProductList) {
                if (product.getType().equals(productType)               // 해당하는 productType
                        && product.getP_count()>product.getP_stock()    // 개수가 재고보다 많을 때
                        && product.getSaleflag()                       // 판매셋팅 true
                ) {
                    result.add(product);
                }
            }

            return result;
        }
	}
```

### 4.  개인적인 후기

#### 4.1\. 배운 점

-   제한된 시간 내에 상상한 것을 구체화하기 위해 노력하는 과정에서 다양한 방법을 시도해 볼 수있었다.
    -   1) 상상한 것을 '플로우 차트'를 그리며 도식화하여 구현해야 할 기능을 세분화하고
    -   2) 세분화한 기능을 '일정관리문서'에 목록으로 도출하여 데드라인을 지킬 수 있도록 노력하고
    -   3) 많은 팀원들과 코드로써 소통할 수 있도록 구현에서 주고받는 매개변수 및 공통 요소를 묶어 간략한 '클래스 다이어그램'을 만들었다.  
          
    -   1-1) 플로우 차트를 세분화한 덕분에 입출력에서 이루어져야할 내용을 명확히 할 수 있었다.
    -   2-1) 일정관리문서를 통해 기능구현 과정의 단계와 몇퍼센트 진행되었지는 전반적으로 파악할 수 있었다.
    -   3-1) 클래스 다이어그램으로 상속 및 다른 클래스와의 관계를 더 쉽게 그려볼 수 있었다.
-   팀작업에서 우리끼리의 규칙을 만드려고 노력한 덕분에 변수명 및 자료구조가 일정부분 통일되었다. 또한 상상 코딩하던 내용이 실제로 구현되다보니 나의 상상의 범위가 어디까지 맞았는지 확인해 볼 수 있어서 좋았다.

#### 4.2. 아쉬운 점/ 발전시켜야 할 점

-   실제 구현에서는 약속하지 못한 다양한 자료구조, 변수를 만들어야해서 세부적인 규칙를 세세하게 짜기보다는 큰 틀에서의 규칙을 만들어야하는 것이 중요하다고 느꼈다.  
    (ex. 변수명 이름을 하나하나 정하기보다, 만들어진 변수를 축약하지 않고 서로 확인가능한 단어들로 만들기 등)
-   열심히 소통하려 노력했지만 더 적극적인 자세가 필요했다. 내 파트가 만들어졌다고 끝이 아니고, 관련된 파트의 팀원이 어떻게 만드는지, 무엇이 문제인지 지속적으로 맞춰가야했다는 것을 깨달았다.

#### 4.3. 다음 프로젝트에서 고려하면 좋을 점

-   깃 사용: 개인으로 작업할때는 상관없었지만, 계속적으로 서로의 코드가 수정되면서 계속적으로 서로의 코드를 저장받고, 수정내용을 확인하고 통합하는 과정에서 시간을 많이 소요하였다. 깃을 통해 형상관리 뿐 아니라, 통합도 편하게 한다면 실 코드를 더 많이 테스트할 시간이 늘어날 것이라 생각한다.  

[##_ImageGrid|kage@bFCUHq/btsy9NecUNu/P62LlKLcGhGfA5KZqAjkuK/img.png,kage@dwF4GF/btsy34B58nD/Y2TUA1wUERYEpOyIqwjsmK/img.png|data-origin-width="289" data-origin-height="606" data-is-animation="false" style="width: 21.3507%; margin-right: 10px;" data-widthpercent="21.6" alt="개인 작업물을 합치기위해 많은 폴더를 생성해야 했음",data-is-animation="false" data-origin-height="520" data-origin-width="900" data-widthpercent="78.4" alt="바뀐 부분을 지속적으로 비교했던 장면" data-filename="blob" style="width: 77.4865%;"|형상관리툴을 사용하지 않아, 모두의 코드를 지속적으로 다운로드 받아 비교했다. -&gt; 깃사용의 필요성을 크게!! 느낌_##]

-   플로우차트: 개발할 수 있도록 명확하고 시각적으로 보일 수 있게 작성해야한다고 느꼈다.

#### 4.4. 프로젝트를 마치며...

더보기

드디어! 프로젝트가 마무리되었다. (일어나서 박수치는 중ㅠ)

 분명한 건, '함께 여서 멀리 갈 수 있었다'라는 사실이다. 새벽까지 코드짜고 강의실에 들어가서 다시 팀회의를 했던 무수한 나날들.. 처음에는 혼자 빨리 진도를 나가기 위해 서둘렀다. 그 과정에서 일부 세세한 과정을 챙기지 못했고, 또 너무 피곤하기에 상대적으로 덜 중요하다고 생각되는 부분은 넘기고 싶었다.(적합한 리스트에 대한 고민, '왜'에 대한 고민 등)

 그러나 프로젝트의 회의를 통해서 '단순한 기능 구현' 보다는, '왜 이렇게 코드를 구현하게 되었는지'가 더 중요하다는 사실을 깨달았다. 왜냐하면 팀 프로젝트라는 것은 우리 모두가 이해할 수 있는 프로그램을 구현하는 것이고, 내가 아닌 다른 이와 같은 결론에 도달하기 위해서는 왜 이렇게 코드를 구현했는지 납득 할 수 있도록 설명해야했기 때문이다. 다른 사람을 설득시키기 위해서는 더 많이 조사하고 공부해야했고, 자바 세미프로젝트기간에 여러번 JAVA를  복습하게 되었다. 정말 힘들었지만, 좋은 코드란 무엇일지 스스로에게 묻고 공부하고 질문했던 시간이 앞으로 나를 지탱할 밑거름이 될거라는 분명한 확신이들어 기쁘다. 지금은 최선의 선택이었지만, 공부하다 보면 더 좋은 코드를 만들수 있겠지? 앞으로의 배움이 더 기대된다!

### **5\. 기타**

#### 5.1. 참고자료

-   기획| 워크플로우  
    [https://www.drawio.com/](https://www.drawio.com/)
-   구현| 인텔리제이  
    [https://www.jetbrains.com/ko-kr/idea/](https://www.jetbrains.com/ko-kr/idea/)
-   구현| 개념 복습: 유튜브 강의\_Java 끝.장.내.기  
    [https://www.youtube.com/watch?v=iN22AgS\_Chk](https://www.youtube.com/watch?v=iN22AgS_Chk)
-   꾸미기| 텍스트 이미지로 변환  
    [https://jdh5202.tistory.com/584](https://jdh5202.tistory.com/584)
-   형상관리| 깃 : 맨땅에서 시작하는 협업을 위한 Github 사용법  
    [https://dhgu-dev.medium.com/%EB%A7%A8%EB%95%85%EC%97%90%EC%84%9C-%EC%8B%9C%EC%9E%91%ED%95%98%EB%8A%94-%ED%98%91%EC%97%85%EC%9D%84-%EC%9C%84%ED%95%9C-github-%EC%82%AC%EC%9A%A9%EB%B2%95-46f64418cf81](https://dhgu-dev.medium.com/%EB%A7%A8%EB%95%85%EC%97%90%EC%84%9C-%EC%8B%9C%EC%9E%91%ED%95%98%EB%8A%94-%ED%98%91%EC%97%85%EC%9D%84-%EC%9C%84%ED%95%9C-github-%EC%82%AC%EC%9A%A9%EB%B2%95-46f64418cf81)
-   형상관리| 깃 : 커밋 메시지 템플릿  
    [https://velog.io/@cnsrn1874/git-%EC%BB%A4%EB%B0%8B-%EB%A9%94%EC%8B%9C%EC%A7%80-%ED%85%9C%ED%94%8C%EB%A6%BF](https://velog.io/@cnsrn1874/git-%EC%BB%A4%EB%B0%8B-%EB%A9%94%EC%8B%9C%EC%A7%80-%ED%85%9C%ED%94%8C%EB%A6%BF)
