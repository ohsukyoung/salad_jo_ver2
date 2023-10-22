import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class IngredientMg implements Impl_admin{

    public final int E_PRINT = 1;                    //-- ���(ȸ��) �߰�
    public final int E_ADD = 2;
    public final int E_MOD = 3;
    public final int E_DEL = 4;
    public final int E_EXIT = 5;

    public static HashMap<String, Member> hm; //-- �ڷᱸ�� ����
    private static BufferedReader br;		         //-- ����ڰ� �Է½� Ȱ��
    private static Integer sel;				         //-- ���� ��
    private static String con;				         //-- ��� ���� ����

    Product product = new Product();

    static
    {
        //hashmap �ڷᱸ�� ����
        hm = new HashMap<String, Member>();

        //BufferedReader ��ü ����
        br = new BufferedReader(new InputStreamReader(System.in));

        // ����� �Է°� �ʱ�ȭ
        sel = 1;
        con = "Y";
    }
    public void menuDisp()
    {
        System.out.println("\n\t[ ������ �޴� ���� ]===========");
        System.out.println("\t1. ��� ��� ���");
        System.out.println("\t2. �ű���� ���");
        System.out.println("\t3. ������� ����");
        System.out.println("\t4. ������� ����");
        System.out.println("\t5. ������ �޴� ȭ������ �̵�");
        System.out.println("\t=================================");
        System.out.print("\t�� �޴� ����(1~5) : ");
    }

    public void menuSelect() throws IOException, NumberFormatException
    {
        sel = Integer.parseInt(br.readLine());
    }
    public void menuRun() throws IOException, ClassNotFoundException
    {
        switch (sel)
        {
            case E_PRINT : ad_print(); break;
            case E_ADD : ad_add(); break;
            case E_MOD : ad_modify(); break;
            case E_DEL : ad_delete(); break;
            case E_EXIT : exit(); break;
            default : System.out.print("\t[!] �޴� ���� ����");
        }
    }
    @Override
    public void ad_print() throws IOException, ClassNotFoundException {

        List<Product> product = CacheData.allProductList;

        System.out.println("\n\t[ 1. ������� ��� ]===============");
        System.out.println("\t1. ��ü ������� ���  \n\t2. ���� ������� ��� ");
        System.out.println("\t==============================");

        int seletCheckNnumber=0;
        int materialNumber=0;
        while (true)
        {
            try{
                System.out.print("\t�� ������ �׸��� ���� �Է� : ");
                seletCheckNnumber = Integer.parseInt(br.readLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
                continue;
            }

            switch (seletCheckNnumber)
            {
                case 1 :
                    System.out.println("\n\t[ 1. ��ü ������� ��� ]");
                    System.out.println("\t==========================================================================================================");
                    System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                            "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�", "�Ǹſ���");
                    System.out.println("\t==========================================================================================================");

                    // Iterator Ȱ���Ͽ� ���
                    Iterator<Product> itList = product.iterator();
                    while (itList.hasNext())
                    {
                        Product itS = itList.next();
                        String saleText = "";
                        saleText = itS.getSaleflag()? "�Ǹ� O": "�Ǹ� X";
                        System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
                                itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                                itS.getP_stock(), itS.getP_price(), saleText);
                    }
                    System.out.println("\t==========================================================================================================");
                    System.out.println();
                    return;

                case 2 :
                    System.out.println("\n\t[ 2. ���� ������� ��� ]");
                    System.out.println("\t==========================================================================================================");
                    System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                            "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�");
                    System.out.println("\t==========================================================================================================");
                    System.out.println("\t*** [�з���ȣ �ȳ�] 1: ������õ, 3:����, 4:���̵�, 5:���̽�, 6:��������, 7:���̵�����, 8:�ҽ�");
                    while (true) {
                        try {
                            System.out.print("\t�� �з���ȣ �Է� : ");
                            materialNumber = Integer.parseInt(br.readLine());

                            boolean found = false; // found ���� �ʱ�ȭ

                            for (int i = 0; i < product.size(); i++) {
                                if (materialNumber == product.get(i).getP_material()) {
                                    found = true;
                                    break; // �ش� �з���ȣ�� ã�����Ƿ� ���� ����
                                }
                            }

                            if (found) {
                                break; // �ùٸ� �з���ȣ�� ã�����Ƿ� ���� ����
                            } else {
                                System.out.println("\t�� ���й�ȣ�� �������� �ʽ��ϴ�.");
                            }
                            break;
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
                        }

                    }


                    // Iterator Ȱ���Ͽ� ������ �з���ȣ�� �ش��ϴ� ������� ���
                    Iterator<Product> itList1 = product.iterator();
                    while (itList1.hasNext())
                    {
                        Product itS = itList1.next();
                        if (itS.getP_material() == materialNumber)
                        {
                            String saleText = "";
                            saleText = itS.getSaleflag()? "�Ǹ� O": "�Ǹ� X";
                            System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n", itS.getP_checkNumber(),
                                    itS.getP_material(), itS.getP_name(), itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                                    itS.getP_stock(), itS.getP_price(), saleText);
                        }
                    }
                    System.out.println("\t==========================================================================================================");
                    System.out.println();
                    return;

                default:
                    System.out.println("\t[!] �߸��� �Է��Դϴ�. �ٽ� �Է��ϼ���.");
                    continue;

            }

        }

    }


    @Override
    public void ad_add() throws IOException,ClassNotFoundException {

        //�ڷᱸ�� ����
        List<Product> list1 = CacheData.allProductList;

        boolean shouldExit = false;
        System.out.println("\n\t[ 2. �ű���� ��� ]");
        System.out.println("\t==========================================================================================================");
        System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�", "�Ǹſ���");
        System.out.println("\t==========================================================================================================");
        System.out.println("\t*** [�з���ȣ �ȳ�] 1: ������õ, 3:����, 4:���̵�, 5:���̽�, 6:��������, 7:���̵�����, 8:�ҽ�");
        System.out.println("\t*** [�Ǹſ��� �ȳ�] 1: �Ǹ� O, 2: �Ǹ� X");

        System.out.print("\t�� ������� �Է� (�����̽��� ����): ");
        String input = br.readLine();

        // ��ũ�������� �����̽��� �����ֱ�
        int addTokenizer = 9;
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        if (tokenizer.countTokens() != addTokenizer) {
            System.out.println("\t[!] �Է��� �׸��� ������ ���� �ʽ��ϴ�.");
            return;
        }

        try{

            int p_checkNumber = Integer.parseInt(tokenizer.nextToken());
            int p_material = Integer.parseInt(tokenizer.nextToken());

            ProductTypeChange productTypeChange = new ProductTypeChange();
            ProductType productType= productTypeChange.ProductTypeChange(p_material);

            String p_name = tokenizer.nextToken();
            String p_unit = tokenizer.nextToken();
            int p_count = Integer.parseInt(tokenizer.nextToken());
            int p_calorie = Integer.parseInt(tokenizer.nextToken());
            int p_stock = Integer.parseInt(tokenizer.nextToken());
            int p_price = Integer.parseInt(tokenizer.nextToken());
            int p_saleflagInt = Integer.parseInt(tokenizer.nextToken());
            boolean p_saleflag = false;
            p_saleflag = p_saleflagInt == 1? true : false;

            List<Product> allProductList;
            allProductList = CacheData.allProductList;

            boolean m = false;
            for (int i = 0; i < allProductList.size(); i++){
                if (p_checkNumber == allProductList.get(i).getP_checkNumber() && allProductList.get(i).getSaleflag()){
                    m = true;
                    break;
                }
            }
            if (m){
                System.out.println("\t[!] �̹� ���й�ȣ�� �����մϴ�.");
                return;
            }
            else {
                System.out.print("\t�� �����Ͻðڽ��ϱ�?(Y/N) : ");
                char x = br.readLine().charAt(0);
                if (x == 'Y' || x == 'y') {
                    Product product = new Product(p_checkNumber, productType, p_material, p_name, p_unit, p_count, p_calorie, p_stock, p_price, p_saleflag);
                    list1.add(product);
                    // ����ȭ �ּ� 231018
//                    FileMg f = new FileMg();
//                    f.list1FileOut();
//                    f.list3FileOut();

                    System.out.println("\t�� ����Ǿ����ϴ�. ��");
                } else {
                    System.out.println("\t[!] �ùٸ� ���� �Է��Ͻÿ�.");
                    return;
                }
            }
        }
        catch (NumberFormatException e){
            System.out.println("\t[!] �߸��� ���� �����Դϴ�. �ٽ� �Է��ϼ���.");
        }
    }



    @Override
    public void ad_modify() throws IOException, ClassNotFoundException {
        List<Product> allProductList = CacheData.allProductList;
//        List<Product> list3 = CacheData.list3;
        int modifyNum = 9;

        System.out.println("\n\t[ 3. ������� ���� ]");
        System.out.print("\t�� �۾��� ����� ���й�ȣ �Է�: ");
        int pointNumber;

        try {
            pointNumber = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("\t[!] �ùٸ� ���й�ȣ�� �Է��ϼ���.");
            return;
        }

        boolean found = false;

        for (int i = 0; i < allProductList.size(); i++) {
            if (pointNumber == allProductList.get(i).getP_checkNumber()) {
                found = true;
                Product itS = allProductList.get(i);

                String saleText = "";
                saleText = itS.getSaleflag()? "�Ǹ� O": "�Ǹ� X";
                System.out.println("\t==========================================================================================================");
                System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                        "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�", "�Ǹſ���");
                System.out.println("\t==========================================================================================================");
                System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                        itS.getP_checkNumber(), itS.getP_material(), itS.getP_name(),
                        itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                        itS.getP_stock(), itS.getP_price(), saleText);
                System.out.println("\t==========================================================================================================");

                System.out.println("\n\t[ ������ ���� ���� ]-----------------------------------------------------------------------");
                System.out.println("\t1.���й�ȣ 2. �з���ȣ 3. �̸� 4. ���� 5. ���� 6. Į�θ� 7. ������� 8. �ݾ� 9. �Ǹſ���");
                System.out.println("\t----------------------------------------------------------------------------------------");
                System.out.println();

                while (true) {
                    System.out.print("\t�� ������ ������ ���� �Է� (0: ���� �Ϸ�) : ");
                    int choice;

                    try {
                        choice = Integer.parseInt(br.readLine());
                    } catch (NumberFormatException e) {
                        System.out.println("\t[!] �ùٸ� ������ �Է��ϼ���.");
                        return;
                    }

                    if (choice == 0) {
                        // ���� �Ϸ� ��, ����� ������ ����
                        // ����ȭ �ּ� 231018
//                        FileMg f = new FileMg();
//                        f.list1FileOut();
//                        f.list3FileOut();
                        System.out.println("\t�� �����Ǿ����ϴ�. ��");
                        return; // �޼ҵ带 ���������ϴ�.
                    } else if (choice < 1 || choice > modifyNum) {
                        System.out.println("\t[!] �߸��� �����Դϴ�. �ٽ� �����ϼ���.");
                    } else {
                        if(choice == 9){
                            System.out.println("\t*** [�Ǹſ��� �ȳ�] 1: �Ǹ� O, 2: �Ǹ� X");
                        }
                        System.out.print("\t�� ���ο� �� �Է� : ");
                        String newValue = br.readLine();
                        try {
                            switch (choice) {
                                case 1:
                                    int newCheckNumber = Integer.parseInt(newValue);
                                    itS.setP_checkNumber(newCheckNumber);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_checkNumber(newCheckNumber);
//                                        }
//                                    }
                                    break;
                                case 2:
                                    int newMaterial = Integer.parseInt(newValue);
                                    itS.setP_material(newMaterial);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_material(newMaterial);
//                                        }
//                                    }
                                    break;
                                case 3:
                                    itS.setP_name(newValue);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_name(newValue);
//                                        }
//                                    }
                                    break;
                                case 4:
                                    itS.setP_unit(newValue);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_unit(newValue);
//                                        }
//                                    }
                                    break;
                                case 5:
                                    int newCount = Integer.parseInt(newValue);
                                    itS.setP_count(newCount);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_count(newCount);
//                                        }
//                                    }
                                    break;
                                case 6:
                                    int newCalorie = Integer.parseInt(newValue);
                                    itS.setP_calorie(newCalorie);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_calorie(newCalorie);
//                                        }
//                                    }
                                    break;
                                case 7:
                                    int newStock = Integer.parseInt(newValue);
                                    itS.setP_stock(newStock);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_stock(newStock);
//                                        }
//                                    }
                                    break;
                                case 8:
                                    int newPrice = Integer.parseInt(newValue);
                                    itS.setP_price(newPrice);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setP_price(newPrice);
//                                        }
//                                    }
                                    break;
                                case 9:
                                    int newSale = Integer.parseInt(newValue);
                                    boolean p_saleflag = false;
                                    p_saleflag = newSale == 1? true : false;
                                    itS.setSaleflag(p_saleflag);
//                                    for (Product p : allProductList) {
//                                        if (p.getP_checkNumber() == pointNumber) {
//                                            p.setSaleflag(p_saleflag);
//                                        }
//                                    }
                                    break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("\t[!] �ùٸ� ���� �Է��ϼ���.");
                        }
                    }
                }
            }
        }

        if (!found) {
            System.out.println("\t[!] ���й�ȣ�� ��ġ���� �ʽ��ϴ�.");
        }

    }



    @Override
    public void ad_delete() throws IOException, ClassNotFoundException {
        List<Product> product = CacheData.allProductList;
        List<Product> list3 = CacheData.list3;

        System.out.println("\n\t[ 4. ������� ���� ]");
        System.out.print("\t�� �۾��� ����� ���й�ȣ �Է� (�ڷΰ���:0) : ");
        int pointNumber;


        try {
            pointNumber = Integer.parseInt(br.readLine());
            if(pointNumber==0)
            {
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("\t[!] �ùٸ� ���й�ȣ�� �Է��ϼ���.");
            return;
        }

        boolean found = false;

        int deleteIndex = -1; // ������ �������� �ε��� �ʱ�ȭ

        for (int i = 0; i < product.size(); i++) {
            if (pointNumber == product.get(i).getP_checkNumber()) {
                found = true;
                Iterator<Product> itList;
                itList = product.iterator();
                Product itS = product.get(i);

                String saleText = "";
                saleText = itS.getSaleflag()? "�Ǹ� O": "�Ǹ� X";
                System.out.println("\t==========================================================================================================");
                System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                        "���й�ȣ", "�з���ȣ", "�̸�", "����", "����", "Į�θ�", "�������", "�ݾ�", "�Ǹſ���");
                System.out.println("\t==========================================================================================================");
                System.out.printf("\t|| %5s || %5s || %9s || %5s || %5s || %5s || %5s || %5s || %5s ||\n",
                        itS.getP_checkNumber(), itS.getP_material(), itS.getP_name(),
                        itS.getP_unit(), itS.getP_count(), itS.getP_calorie(),
                        itS.getP_stock(), itS.getP_price(), saleText);
                System.out.println("\t==========================================================================================================");
                System.out.println();
                System.out.println();
                System.out.print("\t�� �����Ͻðڽ��ϱ�? (Y/N) : ");
                char x = br.readLine().charAt(0);

                if (x == 'Y' || x == 'y') {
                    deleteIndex = i; // ���� ��� ��ǰ�� �ε��� ����
                    for (Iterator<Product> iterator = list3.iterator(); iterator.hasNext(); ) {
                        Product p = iterator.next();
                        if (p.getP_checkNumber() == pointNumber) {
                            iterator.remove(); // list3���� �����ϰ� ����
                        }
                    }
                    break;
                } else if (x == 'N' || x == 'n') {
                    return; // 'N'�� �Է��ϸ� �޼ҵ带 �������ɴϴ�.
                } else {
                    System.out.println("\t[!] �ùٸ� ���� �Է��Ͻÿ�.");
                    return;
                }
            }
        }

        if (!found) {
            System.out.println("\t[!] ���й�ȣ�� ��ġ���� �ʽ��ϴ�.");
            return; // ��ġ���� ������ ���� �۾��� ���� �ʰ� ����
        }

        // ���� ��� ��ᰡ ������ ��쿡�� ���� ����
        if (deleteIndex != -1) {
            product.remove(deleteIndex);

            // ����ȭ �ּ� 231018
//            FileMg f = new FileMg();
//            f.list1FileOut();
//            f.list3FileOut();
            System.out.println("\t�� �����Ǿ����ϴ�. ��");
        }

    }
    public void exit()
    {
        System.out.println("\n\t�� ������ �޴��� ���ư��ϴ�. ��");
        KioskMg.ingredientflag = false;
    }
}
