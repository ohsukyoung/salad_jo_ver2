import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.List;

// 장바구니 클래스
class cart
{

    private static String memPw;
    private static BufferedReader br;           //입력 시 활용
    private static String con;                  //Y,N으로 계속 진행 여부
    private static Integer sel;                 //선택값
    static String id;
    private static int emptypoint;

    public static int payPoint;

    static
    {
        //al = new ArrayList<Object>();                               //ArrayList 자료구조 생성
        br = new BufferedReader(new InputStreamReader(System.in));  //BufferedReader 객체 생성
        sel = 1;                                                    //사용자 입력값 초기화
    }
    private static int tot;
    public static int getTot()
    {
        if (tot<0)
        {
            return -1;
        }
        return tot;
    }

//    private static HashMap<String,Member> hm = new HashMap<String,Member>();

    Member m = new Member();

    static int py=0;
    static int wan=0;



    //수경---
    static OrderSetting oSetting = new OrderSetting();


    //장바구니 출력 메소드
    public static void menuDis()
    {
        List<Order> OrderList = CacheData.userSelectOrderList;

        if(OrderList.get(OrderList.size()-1).getO_totPrice() == 0){
            try {
                System.out.println("\n\t「 결제금액이 0원으로 처음으로 되돌아갑니다. 」");
                ProductService productService = new ProductService(); // ProductService 객체 생성


                Kiosk ks = new Kiosk(productService);                   //장바구니 비운 후 다시 메뉴 선택창으로 가기
                ks.kioskStart();
            } catch (IOException e) {
                System.out.println("e.toString: " + e.toString());
                System.out.println("e.getMessage: " + e.getMessage());
                System.out.println("printStackTrace................");
                e.printStackTrace();
            }
        }

        //선택 재료들, 칼로리 총합, 금액 총합 보여주기
        //oSetting.set_Print(outerList);

        System.out.println("\n\t[장바구니]===============");
        System.out.println("\t1. 장바구니 비우기");
        System.out.println("\t2. 결제");
        System.out.println("\t3. 추가 주문");
        System.out.println("\t=======================");
        System.out.print("\t▶ 장바구니(1-3) : ");
    }

    //메뉴 선택 메소드
    public static void menuSel(int userSelect) throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        try
        {
            sel = Integer.parseInt(br.readLine());
        }
        catch (NumberFormatException e)
        {
            System.out.println("\t[!] 숫자를 넣어주세요.");
            menuDis();
            menuSel(userSelect);
            menuR(userSelect);
        }
    }

    //선택된 메뉴 실행에 따른 기능 호출 메소드
    public static void menuR(int userSelect) throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        //cartMe() 메소드 활용하여 처리
        switch (sel)
        {
            case cartMe.e_del : cartdel() ; break;
            case cartMe.e_pay : cartpay() ; break;
            case cartMe.e_add : cartadd(userSelect) ; break;
            default : System.out.println("\n\t[!] 메뉴 선택 오류");
        }
    }

    //자료구조에 장바구니 비우기 메소드
    public static void cartdel() throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        List<OrderValues> OrderValuesList = CacheData.selectValueCart;

        System.out.print("\n\t▶ 장바구니를 비우시겠습니까(Y/N)? : ");
        con = br.readLine().toUpperCase();

        if (con.equals("Y"))                                        //장바구니를 비우기로 했으면 자료구조 비우기
        {
            OrderList.clear();  // 장바구니를 비우기 (모든 주문 정보 제거)
            OrderValuesList.clear();
//            OrderList.get(OrderList.size()-1).innerList.clear();  //TODO 왜 지워지지 않는지 조사



            System.out.println();
            System.out.println("\t「 장바구니를 비웠습니다. 」");
            System.out.println();

            ProductService productService = new ProductService(); // ProductService 객체 생성

            Kiosk ks = new Kiosk(productService);                   //장바구니 비운 후 다시 메뉴 선택창으로 가기
            ks.kioskStart();
        }
        else if (con.equals("N"))                                   //장바구니를 비우지 않는다 하면 장바구니로 돌아가기
        {
            OrderSetting orderSetting = new OrderSetting();
            orderSetting.calculateOrderTotal();
            orderSetting.printOrderList();
            System.out.println();
            System.out.println("\t「 장바구니로 돌아갑니다. 」");
            System.out.println();
        }
        else
        {
            cartdel();
        }
    }

    //결제 메소드
    public static void cartpay() throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        py=0;

        // 회원 여부에 따라 총 결제 금액 계산
        int memberTotal=0;

        System.out.println("\n\t[ 회원/비회원 ]===========");
        System.out.println("\t1. 회원");
        System.out.println("\t2. 비회원");
        System.out.println("\t=========================");
        System.out.print("\t▶ 선택: ");
        try
        {
            sel = Integer.parseInt(br.readLine());
        }
        catch (NumberFormatException e)
        {
            System.out.println("\t[!] 올바른 숫자를 입력하세요.");
            cartpay();
        }

        //회원일 때
        if (sel==1)
        {
            wan = 0;
            int countLogin = 1;
            boolean loginFlag = true;
            while(loginFlag)
            {
                System.out.println();
                System.out.print("\t▶ ID 입력(전화번호) : ");
                id = br.readLine();
                System.out.print("\t▶ Password 입력(4자리) : ");
                memPw = br.readLine();

                if (!MemberMg.hm.containsKey(id))
                {
                    System.out.println("\n\t[!] 입력하신 ID가 존재하지 않습니다.");
                    countLogin++;
                }
                else
                {
                    if (MemberMg.hm.get(id).getMemPw().equals(memPw))
                    {
                        System.out.println("\n\t「 아이디와 비밀번호가 일치합니다. 」");
                        break;
                    }
                    else
                    {
                        System.out.println("\n\t[!] 비밀번호가 일치하지 않습니다.");
                    }
                }
                if (countLogin==4)
                {
                    System.out.println("\n\t[!] 존재하지 않는 ID를 3번 이상 입력해서 회원/비회원 선택으로 돌아갑니다.");
                    loginFlag = false;
                    cartpay();
                }
            }
            // 회원일 때 총 결제 금액 설정
            pointuse(memberTotal);
        }

        //비회원일 때
        else if (sel==2)
        {
            wan = 0;
            memberTotal = OrderList.get(OrderList.size() - 1).getO_totPrice();  // 비회원일 때 총 결제 금액 설정
            System.out.print("\n\t▶ 회원가입을 하시겠습니까(Y/N)? : ");
            con = br.readLine().toUpperCase();

            if (con.equals("Y"))
            {
                System.out.println();
                System.out.println("\t「 회원가입을 시작합니다. 」");
                System.out.println("\t「 가입정보를 입력해주세요. 」");
                int countCheck=1;
                boolean checkFlag = true;

                while (checkFlag)
                {
                    boolean idCheck = false;                                         // 선언 및 초기화
                    System.out.print("\t▶ ID 입력(전화번호) : ");
                    id = br.readLine();                                   // 사용자가 ID를 입력
                    char[] arr1 = id.toCharArray();                              // 사용자가 입력한 id를 char 배열 arr1에 쪼개서 담기
                    for (int i=3; i<id.length(); i++)                            // 010 뒷부분은 인덱스 3~10
                    {
                        if ('0'<=arr1[i] && arr1[i]<='9')                        // 010 뒷부분을 0에서 9까지의 숫자형태로 입력했는지 확인
                            idCheck = true;
                    }


                    if (MemberMg.hm.containsKey(id))
                    {
                        System.out.println("\n\t[!] 이미 존재하는 ID입니다.");
                        countCheck++;
                    }
                    else if (id.length()==11 && idCheck && id.substring(0,3).equals("010"))   // 총 11자리가 이고, 010으로 시작하고, 010 뒷부분을 숫자형태로 입력한 경우
                    {
                        String memPw;
                        while(true) {
                            boolean pwCheck = false;                                         // 선언 및 초기화
                            System.out.print("\t▶ Password 입력(숫자 4자리) : ");
                            memPw = br.readLine();                                // 사용자가 Password를 입력
                            char[] arr2 = memPw.toCharArray();                    // 사용자가 입력한 Password를 char 배열 arr2에 쪼개서 담기
                            for (int i = 0; i <memPw.length(); i++)               // 인덱스 0~3
                                if ('0' <= arr2[i] && arr2[i] <= '9')             // 0에서 9까지의 숫자형태로 입력했는지 확인
                                    pwCheck = true;
                            if (memPw.length() == 4 && pwCheck)                  // 총 4자리이거나, 숫자형태로 입력한 경우
                                break;
                            else
                                System.out.println("\n\t[!] Password는 숫자 4자리만 입력가능합니다. 다시 입력해주세요.");
                        }
                        MemberMg.hm.put(id,new Member(id,memPw,1000));
                        System.out.println("\t「 회원가입이 완료되었습니다. 」");
                        sel=1;
                        break;
                    }
                    else
                        System.out.println("\n\t[!] ID는 전화번호만 입력가능합니다. 다시 입력해주세요.");
                    if (countCheck == 4)
                    {
                        System.out.println("\t「 이미 존재하는 ID를 3번 이상 입력해서 회원/비회원 선택으로 돌아갑니다. 」");
                        checkFlag = false;
                        cartpay();
                    }
                }
                pointuse(memberTotal);

            }
            else if (con.equals("N"))
            {
                System.out.println();
                System.out.println("\t「 결제수단으로 갑니다. 」");

                paysel(py,memberTotal);
            }
            else  //Y/N이 아닌 다른 것을 눌렀을 때 결제 메소드로 이동
            {
                System.out.println("\t▶ Y/N을 눌러주세요.");
                cartpay();
            }
        }
        else
        {
            System.out.println("\t[!] 유효하지 않은 선택입니다. 1 또는 2를 입력해주세요.");
            cartpay();
        }
    }


    //추가 주문 메소드(수정 필요)
    public static void cartadd(int userSelect) throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        ProductService productService = new ProductService(); // ProductService 객체 생성

        Kiosk ks = new Kiosk(productService);                   //장바구니 비운 후 다시 메뉴 선택창으로 가기
        ks.menuDisp();
        // 선택값 체크
        SelectMenu selectMenu = new SelectMenu();
        int listSize = 4;
        userSelect = selectMenu.menuSelect(listSize,1);
        ks.menuRun(userSelect);

        // 선택값 리스트에 담기
        OrderSetting orderSetting = new OrderSetting();
        orderSetting.calculateOrderTotal();
        orderSetting.printOrderList();

        cart ca = new cart();               //-- 장바구니 인스턴스 생성
        do
        {
            ca.menuDis();
            ca.menuSel(userSelect);
            ca.menuR(userSelect);
        }
        while (true);

    }

    //포인트 사용 메소드
    public static void pointuse(int memberTotal) throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        py=0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //System.out.println(OrderList.size());
        //tot = 0;
        tot = OrderList.get(OrderList.size()-1).getO_totPrice();
        // 수경--
        //System.out.println(tot);

        System.out.printf("\n\t▶ 보유한 포인트 %d를 사용하시겠습니까(Y/N)? " , MemberMg.hm.get(id).getMemPoint());
        con = br.readLine().toUpperCase();


        // hm.put(id, new Member(id, memPw,hm.get(id).getMemPoint()- 사용포인트))
        //포인트를 사용할 때
        if (con.equals("Y"))
        {
            //포인트 차감 후 결제수단으로 이동
            System.out.print("\t▶ 사용할 포인트 입력 : ");
            py = Integer.parseInt(br.readLine());

            System.out.println();
            wan -= py;

            if (py > MemberMg.hm.get(id).getMemPoint()) {
                System.out.println("\n\t[!] 보유한 포인트보다 많은 포인트를 입력하셨습니다.");
                pointuse(memberTotal);
                return;
            }
            if (py<=0)
            {
                System.out.println("\t[!] 올바르게 입력해주세요.");
                pointuse(memberTotal);
                return;
            }

            if (py > tot) {
                System.out.println("\t[!] 포인트 사용이 총 결제 금액을 초과했습니다.");
                pointuse(memberTotal);
                return;
            }
            tot = OrderList.get(OrderList.size()-1).getO_totPrice()-py;
            emptypoint = py;
//            System.out.println(emptypoint);

            System.out.printf("\t▷ 남은 포인트: %d\n", MemberMg.hm.get(id).getMemPoint()-py);

            paysel(py, memberTotal);

        }
        //포인트를 사용하지 않을 때
        else if (con.equals("N"))
        {
            wan = 0;
            //결제수단으로 이동
            System.out.println();
            System.out.printf("\t▷ 남은 포인트 : %d" , MemberMg.hm.get(id).getMemPoint());
            System.out.println();
            System.out.println("\t「 결제수단으로 갑니다. 」");
            paysel(py, memberTotal);
        }
        else
        {
            pointuse(memberTotal);
        }
    }

    //결제수단 선택하는 메소드
    public static void paysel(int py,int memberTotal) throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        List<OrderValues> OrderValuesList = CacheData.selectValueCart;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int payus=0;
        String paymentMethod = null;

        if (sel==1) {
            // 회원일 경우 총 결제 금액
            memberTotal = OrderList.get(OrderList.size() - 1).getO_totPrice() - py;
            System.out.println("\t「 포인트 차감 후 결제수단으로 이동 」");
            System.out.printf("\t▷ 총 결제 금액은 (%d) 입니다.\n", memberTotal);
        }
        else
        {
            // 비회원일 경우 총 결제 금액
            memberTotal = OrderList.get(OrderList.size() - 1).getO_totPrice();
            System.out.printf("\t▷ 총 결제 금액은 (%d) 입니다.\n", memberTotal);
        }

        int inputsel;
        while (true) {
            System.out.println("\n\t[결제수단]================");
            System.out.println("\t1. 카카오페이");
            System.out.println("\t2. 삼성페이");
            System.out.println("\t3. 일반결제");
            System.out.println("\t4. 결제취소");
            System.out.println("\t=========================");
            System.out.print("\t▶ 결제수단(1-4) : ");
            try {
                inputsel = Integer.parseInt(br.readLine());
                if (inputsel >= 1 && inputsel <= 4) {
                    break;
                } else {
                    System.out.println("\n[!] 1에서 4 사이의 값을 선택하세요.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("\n[!] 올바른 숫자를 입력하세요.");
            }
        }

        switch (inputsel){
            case 1 : paymentMethod = "카카오페이"; break;
            case 2 : paymentMethod = "삼성페이"; break;
            case 3 : paymentMethod = "일반결제"; break;
        }

        OrderList.get(OrderList.size() - 1).setPaymentMethod(paymentMethod);

        System.out.printf("\n\t▷ 총 결제 금액 : %8d\n", memberTotal);

        switch (inputsel) {
            case 1:
                System.out.print("\t▶ 카카오페이 잔액 입력 : ");
                payus = Integer.parseInt(br.readLine());
                break;
            case 2:
                System.out.print("\t▶ 삼성페이 잔액 입력 : ");
                payus = Integer.parseInt(br.readLine());
                break;
            case 3:
                System.out.print("\t▶ 일반결제 잔액 입력 : ");
                payus = Integer.parseInt(br.readLine());
                break;
            case 4:

                OrderList.clear();
                OrderValuesList.clear();
                payPoint=0;
                System.out.print("\t「 결제취소합니다. 」");
                ProductService productService = new ProductService(); // ProductService 객체 생성

                Kiosk ks = new Kiosk(productService);                   //장바구니 비운 후 다시 메뉴 선택창으로 가기
                ks.kioskStart();

                break;
            default:
                System.out.print("\t[!] 1-4중에 선택해주세요.");
                paysel(py, memberTotal);
                return;
        }

        //남은 금액
        int emptypay;
        py=0;

        if (sel == 1)
        {
            emptypay = payus - (memberTotal - py);
        }
        else if (sel == 2)
        {
            emptypay = payus - memberTotal;
        }
        else
        {
            emptypay = payus;
            return;
        }

        if (sel ==1) {
            if (payus < memberTotal) {
                System.out.println("\t[!] 잔액 부족으로 포인트 사용 페이지로 되돌아갑니다.");
                pointuse(memberTotal);
            }
        }
        else if (sel==2)
        {
            if (payus < memberTotal) {
                System.out.println("\t[!] 잔액 부족으로 결제 수단으로 되돌아갑니다.");
                paysel(py, memberTotal);
            }
        }
        System.out.printf("\t▷ 남은 잔액은 %d원 입니다.", emptypay);

        ProductService productService = new ProductService();
        Kiosk ks = new Kiosk(productService);
        if(ks.storeflag)
        {
            System.out.println("\n\t「 [매장] 결제가 완료되었습니다. 감사합니다. 」");
        }else{
            System.out.println("\n\t「 [포장] 결제가 완료되었습니다. 감사합니다. 」");
        }

        //장바구니 재료 증감식
        OrderSetting orderSetting = new OrderSetting();
        orderSetting.calculateOrderDiscount();

        receipt(memberTotal);
    }

    public static void receipt(int memberTotal) throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        List<OrderValues> OrderValuesList = CacheData.selectValueCart;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("\n\t▶ 영수증을 출력하시겠습니까(Y/N)? : ");
        con = br.readLine().toUpperCase();


        if (con.equals("Y"))
        {
			System.out.println("\n┌───────────────────────────────────────────────────────────────┐");
			System.out.println("│       							│");
            System.out.println("│ [ 영수증 ] 샐러드먹조      			                │");

            int i=1,j=1;

            OrderCart oC = new OrderCart();
            OrderList.get(OrderList.size()-1).setO_nowTime(oC.nowTime());
            System.out.println("│ ------------------------------------------------------------  │");
            for (Order order: OrderList){
                System.out.printf("│ %-4d   %-8s \t%-8s \t%-8d \t%-8d│\n", i++, order.getO_name(), order.getO_nowTime(), order.getO_totCalorie(), order.getO_totPrice());
                System.out.println("│ ------------------------------------------------------------  │");

                System.out.printf("│ %-4s) %-4s \t%-4s \t%-8s \t%-8s      │\n", "NO", "제품", "구매수량", "칼로리", "금액");
                for (OrderValues orderValues: OrderValuesList) {
                    System.out.printf("│ %d-%d) %-8s \t%-8s \t%-8d \t%-8d│\n", i-1 , j++, orderValues.getName(), orderValues.getCount(), orderValues.getCalorie() * orderValues.getCount(), orderValues.getPrice() * orderValues.getCount());
                }
            }
			System.out.println("│ ------------------------------------------------------------  │");
            System.out.printf("│ 사용한 포인트 : %d, 총 결제 금액: %d  		        │\n", emptypoint, memberTotal);  // 사용한 포인트와 총 결제 금액 출력
            if (sel==1)
            {
                OrderList.get(OrderList.size()-1).setUsedPoints(emptypoint);
                OrderList.get(OrderList.size()-1).setO_name(id);
                OrderList.get(OrderList.size()-1).setMember(true);
                payPoint = MemberMg.hm.get(id).getMemPoint() - emptypoint + (int)(memberTotal*0.1);
                MemberMg.hm.get(id).setMemPoint(payPoint);
            }else {
                OrderList.get(OrderList.size()-1).setMember(false);
            }
			System.out.println("│ ------------------------------------------------------------  │");
			System.out.println("│ 이용해 주셔서 감사합니다 :>                                     │");
			System.out.println("│       							│");
            System.out.println("└───────────────────────────────────────────────────────────────┘");
        }
        else if (con.equals("N"))
        {
            System.out.println("\t「 결제가 모두 완료됐습니다. 이용해주셔서 감사합니다. 」");
        }
        else
        {
            receipt(memberTotal);
        }
        // 231012 직렬화
//        try {
//            FileMg f = new FileMg();
//            //f.orderOuterFileOut();
//            f.memberFileOut();
//            f.receiptFileOut();
//            //f.orderInnerValuesFileOut();
////            CacheData.orderOuterList = f.orderOuterFileIn();
//            //CacheData.orderInnerValues = f.orderInnerValuesFileIn();
//        } catch (IOException e) {
//            System.out.println("e.toString: " + e.toString());
//            System.out.println("e.getMessage: " + e.getMessage());
//            System.out.println("printStackTrace................");
//            e.printStackTrace();
//        }
        SalesMg salesMg = new SalesMg();
        salesMg.receiptSave();
        payPoint=0;
        OrderList.clear();
        OrderValuesList.clear();


        ProductService productService = new ProductService(); // ProductService 객체 생성

        Kiosk ks = new Kiosk(productService);                   //장바구니 비운 후 다시 메뉴 선택창으로 가기
        ks.kioskStart();
    }
}