import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class KioskMg
{
    public static final int E_STOCKMG = 1; // ��� ����
    public static final int E_INGMG = 2; // ��� ����
    public static final int E_RECMG = 3; // ������õ ����
    public static final int E_SALESMG = 4; // ���� ����
    public static final int E_MEMBERMG = 5; // ȸ�� ����
    public static final int E_KIOSKSTART = 6; // �Ǹ� ����(����� ȭ������ �̵�)
    public static final int E_END = 7; // ����

    private static BufferedReader br;		         //-- ����ڰ� �Է½� Ȱ��
    private static Integer sel;				         //-- ���� ��


//    private static boolean Exit = false;  // ���� ���θ� ��Ÿ���� ����

    public static void exitCart()
    {
        try {
            // ��ü ����ȭ
            FileMg f = new FileMg();
            f.memberFileOut();
            f.receiptFileOut();
            f.list1FileOut();
            f.list2FileOut();
        } catch (IOException e) {
            System.out.println("e.toString: " + e.toString());
            System.out.println("e.getMessage: " + e.getMessage());
            System.out.println("printStackTrace................");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
//        Exit = true;
        System.exit(0);
    }

    MemberMg mm = new MemberMg();
    public static boolean memflag;
    public static boolean salesflag;
    public static boolean stockflag;
    public static boolean ingredientflag;
    public static boolean masterrcflag;
    static
    {
        //BufferedReader ��ü ����
        br = new BufferedReader(new InputStreamReader(System.in));

        // ����� �Է°� �ʱ�ȭ
        sel = 1;

    }


    public static void adMenuDisp()
    {
        System.out.println("\n\t[ Ű����ũ ���� �޴� ���� ]=============");
//        System.out.println("\t1. �Ǹ� ����");
        System.out.println("\t1. ��� ����");
        System.out.println("\t2. ��� ����");
        System.out.println("\t3. ������õ ����");
        System.out.println("\t4. ���� ����");
        System.out.println("\t5. ȸ�� ����");
        System.out.println("\t6. �Ǹ� ����(����� ȭ������ �̵�)");
        System.out.println("\t7. ����");
        System.out.println("\t====================================");
        System.out.print("\t�� �޴� ����(1~7) : ");
    }

    // �޴� ���� �޼ҵ�
    public static void adMenuSelect() throws IOException, NumberFormatException
    {
        sel = Integer.parseInt(br.readLine());
    }

    // �޴� ���࿡ ���� ��� ȣ�� �޼ҵ�
    public static void adMenuRun() throws IOException, ClassNotFoundException {

        MemberMg mm = new MemberMg();
        SalesMg sm = new SalesMg();
        IngredientMg im = new IngredientMg();
        StockMg stm = new StockMg();
        MasterRc masterRc = new MasterRc();


        // Ŭ������ Ȱ���Ͽ� ó��
        if (sel==E_STOCKMG){        // 1. ��� ����
//            @old �ǸŰ��� : ��޴����� �� ��ǰ �ǸŻ��� ������ �� �ֵ��� ������
            /*FoodAdmin foodadmin = new FoodAdmin();
            foodadminflag = true;
            System.out.println("\n\t[ �ǸŰ��� ]===============");
            System.out.printf("\t1. �Ǹ����� ���\n\t2. �Ǹ��׸� ����\n\t3. �Ǹ��׸� ����\n");
            System.out.println("\t=========================");
            while(foodadminflag) {
                try {
                    System.out.print("\t�� �޴�����(1~3) : ");
                    check = Integer.parseInt(br.readLine());
                }
                catch (NumberFormatException e){
                }
                switch (check){
                    case 1:
                        foodadmin.setting_print();
                        return;
                    case 2:
                        foodadmin.product_setting();
                        return;
                    case 3:
                        foodadmin.soldout_management();
                        return;
                    default:
                        System.out.println("\t[!] �Էµ� ���ڰ� ���� �ʽ��ϴ�.");
                        break;
                }
            }*/
            stockflag = true;
            while(stockflag)
            {
                stm.menuDisp();
                stm.menuSelect();
                stm.menuRun();
            }


        }
        else if (sel==E_INGMG){     // 2. ��� ����
            ingredientflag = true;
            while(ingredientflag)
            {
                im.menuDisp();
                im.menuSelect();
                im.menuRun();
            }


        }
        else if (sel==E_RECMG){     // 3. ������õ ����
            masterrcflag = true;
            while(masterrcflag)
            {
                masterRc.menuDisp();
                masterRc.menuSelect();
                masterRc.menuRun();
            }


        }
        else if (sel==E_SALESMG){   // 4. ���� ����
            salesflag = true;
            while(salesflag)
            {
                sm.menuDisp();
                sm.menuSelect();
                sm.menuRun();
            }
        }
        else if (sel==E_MEMBERMG){  // 5. ȸ������
            memflag = true;
            while(memflag)
            {
                mm.menuDisp();
                mm.menuSelect();
                mm.menuRun();
            }
        }
        else if (sel==E_KIOSKSTART) // 6. �ǸŽ���(����� ȭ������ �̵�)
        {
            // 231012 ����ȭ
//            try {
//                f.memberFileOut();
//                f.receiptFileOut();
//
//            } catch (IOException e) {
//                System.out.println("e.toString: " + e.toString());
//                System.out.println("e.getMessage: " + e.getMessage());
//                System.out.println("printStackTrace................");
//                e.printStackTrace();
//            }
            System.out.println("\n\n\t====[[[[[ ����� ȭ�� ]]]]]====");
            ProductService productService = new ProductService(); // ProductService ��ü ����
            Kiosk ks = new Kiosk(productService);
            ks.kioskStart();
//            ad_login.kioskFlag = false;
        }
        else if (sel == E_END)      // 7. ���α׷� ����
        {
            exitCart();
        }
        else
            System.out.print("\t[!] �޴� ���� ����");
    }
}