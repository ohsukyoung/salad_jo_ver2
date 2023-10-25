import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.List;

// ��ٱ��� Ŭ����
class cart
{

    private static String memPw;
    private static BufferedReader br;           //�Է� �� Ȱ��
    private static String con;                  //Y,N���� ��� ���� ����
    private static Integer sel;                 //���ð�
    static String id;
    private static int emptypoint;

    public static int payPoint;

    static
    {
        //al = new ArrayList<Object>();                               //ArrayList �ڷᱸ�� ����
        br = new BufferedReader(new InputStreamReader(System.in));  //BufferedReader ��ü ����
        sel = 1;                                                    //����� �Է°� �ʱ�ȭ
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



    //����---
    static OrderSetting oSetting = new OrderSetting();


    //��ٱ��� ��� �޼ҵ�
    public static void menuDis()
    {
        List<Order> OrderList = CacheData.userSelectOrderList;

        if(OrderList.get(OrderList.size()-1).getO_totPrice() == 0){
            try {
                System.out.println("\n\t�� �����ݾ��� 0������ ó������ �ǵ��ư��ϴ�. ��");
                ProductService productService = new ProductService(); // ProductService ��ü ����


                Kiosk ks = new Kiosk(productService);                   //��ٱ��� ��� �� �ٽ� �޴� ����â���� ����
                ks.kioskStart();
            } catch (IOException e) {
                System.out.println("e.toString: " + e.toString());
                System.out.println("e.getMessage: " + e.getMessage());
                System.out.println("printStackTrace................");
                e.printStackTrace();
            }
        }

        //���� ����, Į�θ� ����, �ݾ� ���� �����ֱ�
        //oSetting.set_Print(outerList);

        System.out.println("\n\t[��ٱ���]===============");
        System.out.println("\t1. ��ٱ��� ����");
        System.out.println("\t2. ����");
        System.out.println("\t3. �߰� �ֹ�");
        System.out.println("\t=======================");
        System.out.print("\t�� ��ٱ���(1-3) : ");
    }

    //�޴� ���� �޼ҵ�
    public static void menuSel(int userSelect) throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        try
        {
            sel = Integer.parseInt(br.readLine());
        }
        catch (NumberFormatException e)
        {
            System.out.println("\t[!] ���ڸ� �־��ּ���.");
            menuDis();
            menuSel(userSelect);
            menuR(userSelect);
        }
    }

    //���õ� �޴� ���࿡ ���� ��� ȣ�� �޼ҵ�
    public static void menuR(int userSelect) throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        //cartMe() �޼ҵ� Ȱ���Ͽ� ó��
        switch (sel)
        {
            case cartMe.e_del : cartdel() ; break;
            case cartMe.e_pay : cartpay() ; break;
            case cartMe.e_add : cartadd(userSelect) ; break;
            default : System.out.println("\n\t[!] �޴� ���� ����");
        }
    }

    //�ڷᱸ���� ��ٱ��� ���� �޼ҵ�
    public static void cartdel() throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        List<OrderValues> OrderValuesList = CacheData.selectValueCart;

        System.out.print("\n\t�� ��ٱ��ϸ� ���ðڽ��ϱ�(Y/N)? : ");
        con = br.readLine().toUpperCase();

        if (con.equals("Y"))                                        //��ٱ��ϸ� ����� ������ �ڷᱸ�� ����
        {
            OrderList.clear();  // ��ٱ��ϸ� ���� (��� �ֹ� ���� ����)
            OrderValuesList.clear();
//            OrderList.get(OrderList.size()-1).innerList.clear();  //TODO �� �������� �ʴ��� ����
            for(Product product : CacheData.allProductList) {       // ����� �������� ����
                product.setP_limitFlag(false);
            }

            System.out.println();
            System.out.println("\t�� ��ٱ��ϸ� ������ϴ�. ��");
            System.out.println();

            ProductService productService = new ProductService(); // ProductService ��ü ����

            Kiosk ks = new Kiosk(productService);                   //��ٱ��� ��� �� �ٽ� �޴� ����â���� ����
            ks.kioskStart();
        }
        else if (con.equals("N"))                                   //��ٱ��ϸ� ����� �ʴ´� �ϸ� ��ٱ��Ϸ� ���ư���
        {
            OrderSetting orderSetting = new OrderSetting();
            orderSetting.calculateOrderTotal();
            orderSetting.printOrderList();
            System.out.println();
            System.out.println("\t�� ��ٱ��Ϸ� ���ư��ϴ�. ��");
            System.out.println();
        }
        else
        {
            cartdel();
        }
    }

    //���� �޼ҵ�
    public static void cartpay() throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        py=0;

        // ȸ�� ���ο� ���� �� ���� �ݾ� ���
        int memberTotal=0;

        System.out.println("\n\t[ ȸ��/��ȸ�� ]===========");
        System.out.println("\t1. ȸ��");
        System.out.println("\t2. ��ȸ��");
        System.out.println("\t=========================");
        System.out.print("\t�� ����: ");
        try
        {
            sel = Integer.parseInt(br.readLine());
        }
        catch (NumberFormatException e)
        {
            System.out.println("\t[!] �ùٸ� ���ڸ� �Է��ϼ���.");
            cartpay();
        }

        //ȸ���� ��
        if (sel==1)
        {
            wan = 0;
            int countLogin = 1;
            boolean loginFlag = true;
            while(loginFlag)
            {
                System.out.println();
                System.out.print("\t�� ID �Է�(��ȭ��ȣ) : ");
                id = br.readLine();
                System.out.print("\t�� Password �Է�(4�ڸ�) : ");
                memPw = br.readLine();

                if (!MemberMg.hm.containsKey(id))
                {
                    System.out.println("\n\t[!] �Է��Ͻ� ID�� �������� �ʽ��ϴ�.");
                    countLogin++;
                }
                else
                {
                    if (MemberMg.hm.get(id).getMemPw().equals(memPw))
                    {
                        System.out.println("\n\t�� ���̵�� ��й�ȣ�� ��ġ�մϴ�. ��");
                        break;
                    }
                    else
                    {
                        System.out.println("\n\t[!] ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                    }
                }
                if (countLogin==4)
                {
                    System.out.println("\n\t[!] �������� �ʴ� ID�� 3�� �̻� �Է��ؼ� ȸ��/��ȸ�� �������� ���ư��ϴ�.");
                    loginFlag = false;
                    cartpay();
                }
            }
            // ȸ���� �� �� ���� �ݾ� ����
            pointuse(memberTotal);
        }

        //��ȸ���� ��
        else if (sel==2)
        {
            wan = 0;
            memberTotal = OrderList.get(OrderList.size() - 1).getO_totPrice();  // ��ȸ���� �� �� ���� �ݾ� ����
            System.out.print("\n\t�� ȸ�������� �Ͻðڽ��ϱ�(Y/N)? : ");
            con = br.readLine().toUpperCase();

            if (con.equals("Y"))
            {
                System.out.println();
                System.out.println("\t�� ȸ�������� �����մϴ�. ��");
                System.out.println("\t�� ���������� �Է����ּ���. ��");
                int countCheck=1;
                boolean checkFlag = true;

                while (checkFlag)
                {
                    boolean idCheck = false;                                         // ���� �� �ʱ�ȭ
                    System.out.print("\t�� ID �Է�(��ȭ��ȣ) : ");
                    id = br.readLine();                                   // ����ڰ� ID�� �Է�
                    char[] arr1 = id.toCharArray();                              // ����ڰ� �Է��� id�� char �迭 arr1�� �ɰ��� ���
                    for (int i=3; i<id.length(); i++)                            // 010 �޺κ��� �ε��� 3~10
                    {
                        if ('0'<=arr1[i] && arr1[i]<='9')                        // 010 �޺κ��� 0���� 9������ �������·� �Է��ߴ��� Ȯ��
                            idCheck = true;
                    }


                    if (MemberMg.hm.containsKey(id))
                    {
                        System.out.println("\n\t[!] �̹� �����ϴ� ID�Դϴ�.");
                        countCheck++;
                    }
                    else if (id.length()==11 && idCheck && id.substring(0,3).equals("010"))   // �� 11�ڸ��� �̰�, 010���� �����ϰ�, 010 �޺κ��� �������·� �Է��� ���
                    {
                        String memPw;
                        while(true) {
                            boolean pwCheck = false;                                         // ���� �� �ʱ�ȭ
                            System.out.print("\t�� Password �Է�(���� 4�ڸ�) : ");
                            memPw = br.readLine();                                // ����ڰ� Password�� �Է�
                            char[] arr2 = memPw.toCharArray();                    // ����ڰ� �Է��� Password�� char �迭 arr2�� �ɰ��� ���
                            for (int i = 0; i <memPw.length(); i++)               // �ε��� 0~3
                                if ('0' <= arr2[i] && arr2[i] <= '9')             // 0���� 9������ �������·� �Է��ߴ��� Ȯ��
                                    pwCheck = true;
                            if (memPw.length() == 4 && pwCheck)                  // �� 4�ڸ��̰ų�, �������·� �Է��� ���
                                break;
                            else
                                System.out.println("\n\t[!] Password�� ���� 4�ڸ��� �Է°����մϴ�. �ٽ� �Է����ּ���.");
                        }
                        MemberMg.hm.put(id,new Member(id,memPw,1000));
                        System.out.println("\t�� ȸ�������� �Ϸ�Ǿ����ϴ�. ��");
                        sel=1;
                        break;
                    }
                    else
                        System.out.println("\n\t[!] ID�� ��ȭ��ȣ�� �Է°����մϴ�. �ٽ� �Է����ּ���.");
                    if (countCheck == 4)
                    {
                        System.out.println("\t�� �̹� �����ϴ� ID�� 3�� �̻� �Է��ؼ� ȸ��/��ȸ�� �������� ���ư��ϴ�. ��");
                        checkFlag = false;
                        cartpay();
                    }
                }
                pointuse(memberTotal);

            }
            else if (con.equals("N"))
            {
                System.out.println();
                System.out.println("\t�� ������������ ���ϴ�. ��");

                paysel(py,memberTotal);
            }
            else  //Y/N�� �ƴ� �ٸ� ���� ������ �� ���� �޼ҵ�� �̵�
            {
                System.out.println("\t�� Y/N�� �����ּ���.");
                cartpay();
            }
        }
        else
        {
            System.out.println("\t[!] ��ȿ���� ���� �����Դϴ�. 1 �Ǵ� 2�� �Է����ּ���.");
            cartpay();
        }
    }


    //�߰� �ֹ� �޼ҵ�(���� �ʿ�)
    public static void cartadd(int userSelect) throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        ProductService productService = new ProductService(); // ProductService ��ü ����

        Kiosk ks = new Kiosk(productService);                   //��ٱ��� ��� �� �ٽ� �޴� ����â���� ����
        ks.menuDisp();
        // ���ð� üũ
        SelectMenu selectMenu = new SelectMenu();
        int listSize = 4;
        userSelect = selectMenu.menuSelect(listSize,1);
        ks.menuRun(userSelect);

        // ���ð� ����Ʈ�� ���
        OrderSetting orderSetting = new OrderSetting();
        orderSetting.calculateOrderTotal();
        orderSetting.printOrderList();

        cart ca = new cart();               //-- ��ٱ��� �ν��Ͻ� ����
        do
        {
            ca.menuDis();
            ca.menuSel(userSelect);
            ca.menuR(userSelect);
        }
        while (true);

    }

    //����Ʈ ��� �޼ҵ�
    public static void pointuse(int memberTotal) throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        py=0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //System.out.println(OrderList.size());
        //tot = 0;
        tot = OrderList.get(OrderList.size()-1).getO_totPrice();
        // ����--
        //System.out.println(tot);

        System.out.printf("\n\t�� ������ ����Ʈ %d�� ����Ͻðڽ��ϱ�(Y/N)? " , MemberMg.hm.get(id).getMemPoint());
        con = br.readLine().toUpperCase();


        // hm.put(id, new Member(id, memPw,hm.get(id).getMemPoint()- �������Ʈ))
        //����Ʈ�� ����� ��
        if (con.equals("Y"))
        {
            //����Ʈ ���� �� ������������ �̵�
            System.out.print("\t�� ����� ����Ʈ �Է� : ");
            py = Integer.parseInt(br.readLine());

            System.out.println();
            wan -= py;

            if (py > MemberMg.hm.get(id).getMemPoint()) {
                System.out.println("\n\t[!] ������ ����Ʈ���� ���� ����Ʈ�� �Է��ϼ̽��ϴ�.");
                pointuse(memberTotal);
                return;
            }
            if (py<=0)
            {
                System.out.println("\t[!] �ùٸ��� �Է����ּ���.");
                pointuse(memberTotal);
                return;
            }

            if (py > tot) {
                System.out.println("\t[!] ����Ʈ ����� �� ���� �ݾ��� �ʰ��߽��ϴ�.");
                pointuse(memberTotal);
                return;
            }
            tot = OrderList.get(OrderList.size()-1).getO_totPrice()-py;
            emptypoint = py;
//            System.out.println(emptypoint);

            System.out.printf("\t�� ���� ����Ʈ: %d\n", MemberMg.hm.get(id).getMemPoint()-py);

            paysel(py, memberTotal);

        }
        //����Ʈ�� ������� ���� ��
        else if (con.equals("N"))
        {
            wan = 0;
            //������������ �̵�
            System.out.println();
            System.out.printf("\t�� ���� ����Ʈ : %d" , MemberMg.hm.get(id).getMemPoint());
            System.out.println();
            System.out.println("\t�� ������������ ���ϴ�. ��");
            paysel(py, memberTotal);
        }
        else
        {
            pointuse(memberTotal);
        }
    }

    //�������� �����ϴ� �޼ҵ�
    public static void paysel(int py,int memberTotal) throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        List<OrderValues> OrderValuesList = CacheData.selectValueCart;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int payus=0;
        String paymentMethod = null;

        if (sel==1) {
            // ȸ���� ��� �� ���� �ݾ�
            memberTotal = OrderList.get(OrderList.size() - 1).getO_totPrice() - py;
            System.out.println("\t�� ����Ʈ ���� �� ������������ �̵� ��");
            System.out.printf("\t�� �� ���� �ݾ��� (%d) �Դϴ�.\n", memberTotal);
        }
        else
        {
            // ��ȸ���� ��� �� ���� �ݾ�
            memberTotal = OrderList.get(OrderList.size() - 1).getO_totPrice();
            System.out.printf("\t�� �� ���� �ݾ��� (%d) �Դϴ�.\n", memberTotal);
        }

        int inputsel;
        while (true) {
            System.out.println("\n\t[��������]================");
            System.out.println("\t1. īī������");
            System.out.println("\t2. �Ｚ����");
            System.out.println("\t3. �Ϲݰ���");
            System.out.println("\t4. �������");
            System.out.println("\t=========================");
            System.out.print("\t�� ��������(1-4) : ");
            try {
                inputsel = Integer.parseInt(br.readLine());
                if (inputsel >= 1 && inputsel <= 4) {
                    break;
                } else {
                    System.out.println("\n[!] 1���� 4 ������ ���� �����ϼ���.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("\n[!] �ùٸ� ���ڸ� �Է��ϼ���.");
            }
        }

        switch (inputsel){
            case 1 : paymentMethod = "īī������"; break;
            case 2 : paymentMethod = "�Ｚ����"; break;
            case 3 : paymentMethod = "�Ϲݰ���"; break;
        }

        OrderList.get(OrderList.size() - 1).setPaymentMethod(paymentMethod);

        System.out.printf("\n\t�� �� ���� �ݾ� : %8d\n", memberTotal);

        switch (inputsel) {
            case 1:
                System.out.print("\t�� īī������ �ܾ� �Է� : ");
                payus = Integer.parseInt(br.readLine());
                break;
            case 2:
                System.out.print("\t�� �Ｚ���� �ܾ� �Է� : ");
                payus = Integer.parseInt(br.readLine());
                break;
            case 3:
                System.out.print("\t�� �Ϲݰ��� �ܾ� �Է� : ");
                payus = Integer.parseInt(br.readLine());
                break;
            case 4:

                OrderList.clear();
                OrderValuesList.clear();
                for(Product product : CacheData.allProductList) {       // ����� �������� ����
                    product.setP_limitFlag(false);
                }
                payPoint=0;
                System.out.print("\t�� ��������մϴ�. ��");
                ProductService productService = new ProductService(); // ProductService ��ü ����

                Kiosk ks = new Kiosk(productService);                   //��ٱ��� ��� �� �ٽ� �޴� ����â���� ����
                ks.kioskStart();

                break;
            default:
                System.out.print("\t[!] 1-4�߿� �������ּ���.");
                paysel(py, memberTotal);
                return;
        }

        //���� �ݾ�
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
                System.out.println("\t[!] �ܾ� �������� ����Ʈ ��� �������� �ǵ��ư��ϴ�.");
                pointuse(memberTotal);
            }
        }
        else if (sel==2)
        {
            if (payus < memberTotal) {
                System.out.println("\t[!] �ܾ� �������� ���� �������� �ǵ��ư��ϴ�.");
                paysel(py, memberTotal);
            }
        }
        System.out.printf("\t�� ���� �ܾ��� %d�� �Դϴ�.", emptypay);

        ProductService productService = new ProductService();
        Kiosk ks = new Kiosk(productService);
        if(ks.storeflag)
        {
            System.out.println("\n\t�� [����] ������ �Ϸ�Ǿ����ϴ�. �����մϴ�. ��");
        }else{
            System.out.println("\n\t�� [����] ������ �Ϸ�Ǿ����ϴ�. �����մϴ�. ��");
        }

        //��ٱ��� ��� ������
        OrderSetting orderSetting = new OrderSetting();
        orderSetting.calculateOrderDiscount();

        receipt(memberTotal);
    }

    public static void receipt(int memberTotal) throws IOException
    {
        List<Order> OrderList = CacheData.userSelectOrderList;
        List<OrderValues> OrderValuesList = CacheData.selectValueCart;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("\n\t�� �������� ����Ͻðڽ��ϱ�(Y/N)? : ");
        con = br.readLine().toUpperCase();


        if (con.equals("Y"))
        {
			System.out.println("\n����������������������������������������������������������������������������������������������������������������������������������");
			System.out.println("��       							��");
            System.out.println("�� [ ������ ] ���������      			                ��");

            int i=1,j=1;

            OrderCart oC = new OrderCart();
            OrderList.get(OrderList.size()-1).setO_nowTime(oC.nowTime());
            System.out.println("�� ------------------------------------------------------------  ��");
            for (Order order: OrderList){
                System.out.printf("�� %-4d   %-8s \t%-8s \t%-8d \t%-8d��\n", i++, order.getO_name(), order.getO_nowTime(), order.getO_totCalorie(), order.getO_totPrice());
                System.out.println("�� ------------------------------------------------------------  ��");

                System.out.printf("�� %-4s) %-4s \t%-4s \t%-8s \t%-8s      ��\n", "NO", "��ǰ", "���ż���", "Į�θ�", "�ݾ�");
                for (OrderValues orderValues: OrderValuesList) {
                    System.out.printf("�� %d-%d) %-8s \t%-8s \t%-8d \t%-8d��\n", i-1 , j++, orderValues.getName(), orderValues.getCount(), orderValues.getCalorie() * orderValues.getCount(), orderValues.getPrice() * orderValues.getCount());
                }
            }
			System.out.println("�� ------------------------------------------------------------  ��");
            System.out.printf("�� ����� ����Ʈ : %d, �� ���� �ݾ�: %d  		        ��\n", emptypoint, memberTotal);  // ����� ����Ʈ�� �� ���� �ݾ� ���
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
			System.out.println("�� ------------------------------------------------------------  ��");
			System.out.println("�� �̿��� �ּż� �����մϴ� :>                                     ��");
			System.out.println("��       							��");
            System.out.println("����������������������������������������������������������������������������������������������������������������������������������");
        }
        else if (con.equals("N"))
        {
            System.out.println("\t�� ������ ��� �Ϸ�ƽ��ϴ�. �̿����ּż� �����մϴ�. ��");
        }
        else
        {
            receipt(memberTotal);
        }
        // 231012 ����ȭ
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
        for(Product product : CacheData.allProductList) {       // ����� �������� ����
            product.setP_limitFlag(false);
        }

        ProductService productService = new ProductService(); // ProductService ��ü ����

        Kiosk ks = new Kiosk(productService);                   //��ٱ��� ��� �� �ٽ� �޴� ����â���� ����
        ks.kioskStart();
    }
}