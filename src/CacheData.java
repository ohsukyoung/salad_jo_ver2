import java.util.ArrayList;
import java.util.List;

public class CacheData {
    static List<Order> userSelectOrderList = new ArrayList<>();         // ����� īƮ ����
    static List<OrderValues> selectValueCart;                           // ����� ���ð�
    static List<Product> allProductList = new ArrayList<>();            // ������õ�̿�(������ ������, ����, ���̵�, �����弼����� ��)�� ����Ʈ
    static List<MasterRc> masterRcList = new ArrayList<>();             // ������õ ����Ʈ

    static String SaleTrue = "�Ǹ� O";
    static String SaleFalse = " �Ǹ� X";

    public void testData() {
        // ����� ���� ����Ʈ �� �Է��� ���� ù��° ��ü �߰�
        userSelectOrderList.add(new Order("1", "20231012100200", 0, 0));
        selectValueCart = userSelectOrderList.get(userSelectOrderList.size() - 1).innerList;

        // [Setting Data] AllProductList
//        allProductList.add(new Product(1, ProductType.S_BASE, 1, "�����", "80g", 50, 19, 5, 2000, true));
//        allProductList.add(new Product(2,ProductType.S_BASE,1,"������","80g",50,19,5,2000,true));
//        allProductList.add(new Product(3,ProductType.S_BASE,1,"�","170g",50,225,5,1000,true));
//        allProductList.add(new Product(4,ProductType.S_MAIN,2,"�߰��","50g",50,60,5,2500,true));
//        allProductList.add(new Product(5,ProductType.S_MAIN,2,"�Ұ��","40g",50,108,5,3000,true));
//        allProductList.add(new Product(6, ProductType.S_MAIN, 2, "����", "60g", 50, 101, 5, 2500, true));
//        allProductList.add(new Product(7,ProductType.S_MAIN,2,"����","50g",50,60,5,2800,true));
//        allProductList.add(new Product(8, ProductType.S_MAIN, 2, "���", "50g", 50, 71, 5, 1000, true));
//        allProductList.add(new Product(9, ProductType.S_SIDE, 7, "�丶��", "40g", 50, 7, 5, 400, true));
//        allProductList.add(new Product(10, ProductType.S_SIDE, 7, "�ø���", "25g", 50, 46, 5, 700, true));
//        allProductList.add(new Product(11, ProductType.S_SIDE, 7, "ũ������", "10g", 50, 31, 5, 700, true));
//        allProductList.add(new Product(12, ProductType.S_SIDE, 7, "��ٶ���", "30g", 50, 25, 5, 1000, true));
//        allProductList.add(new Product(13, ProductType.S_SIDE, 7, "������", "30g", 50, 39, 5, 1200, true));
//        allProductList.add(new Product(14,ProductType.S_SIDE,7,"����","30g",50,2,5,1300,true));
//        allProductList.add(new Product(15,ProductType.S_SIDE,7,"����Ÿġ��","14g",50,54,5,1000,true));
//        allProductList.add(new Product(16,ProductType.S_SIDE,7,"�긮Ÿġ��","14g",50,54,5,1000,true));
//        allProductList.add(new Product(17,ProductType.S_SOURCE,8,"������Ż","50g",50,128,5,500,true));
//        allProductList.add(new Product(18,ProductType.S_SOURCE,8,"�߻��","50g",50,127,5,500,true));
//        allProductList.add(new Product(19, ProductType.S_SOURCE, 8, "����", "50g", 50, 239, 5, 500, true));
//        allProductList.add(new Product(20, ProductType.S_SOURCE, 8, "ũ����", "50g", 50, 237, 5, 500, true));
//        allProductList.add(new Product(21,ProductType.S_SOURCE,8,"ĥ��","50g",50,237,5,500,true));
//        allProductList.add(new Product(22,ProductType.S_SOURCE,8,"�������","50g",50,250,5,500,true));
//        allProductList.add(new Product(23, ProductType.DRINK, 3, "�ݶ�", "250ml", 50, 80, 5, 1500, false));
//        allProductList.add(new Product(24, ProductType.DRINK, 3, "���̴�", "250ml", 50, 110, 5, 1500, true));
//        allProductList.add(new Product(25, ProductType.DRINK, 3, "�ݶ�Zero", "250ml", 50, 0, 5, 1600, false));
//        allProductList.add(new Product(26, ProductType.DRINK, 3, "���̴�Zero", "250ml", 50, 0, 5, 1600, true));
//        allProductList.add(new Product(27,ProductType.SIDE,4,"����̽���","100g",243,150,5,2500,true));
//        allProductList.add(new Product(28,ProductType.SIDE,4,"��ġ���","100g",243,150,5,2500,true));
//        allProductList.add(new Product(29, ProductType.SIDE, 4, "����ƾ��", "70g", 50, 414, 5, 1200, true));

        /*
        // @old [Setting Data] MasterProductList : old �����̳�, ���� ����Ḧ �Ѵ��� ���� ���� ���ܵ�
        List<Product> r_products1 = new ArrayList<>();
        List<Product> r_products2 = new ArrayList<>();
        List<Product> r_products3 = new ArrayList<>();

        Product product1 = new Product(4, ProductType.S_MAIN, 2, "�߰��", "50g", 50, 60, 5, 2500, true);
        Product product2 = new Product(11, ProductType.S_SIDE, 7, "ũ������", "10g", 50, 31, 5, 700, true);
        Product product3 = new Product(19, ProductType.S_SOURCE, 8, "����", "50g", 50, 239, 5, 500, true);
        r_products1.add(product1);
        r_products1.add(product2);
        r_products1.add(product3);
        masterRcList.add(new MasterRc(1, ProductType.RCMND, "����ġŲ������", 4000, 3500, r_products1, 10, 50, true));

        Product product11 = new Product(8, ProductType.S_MAIN, 2, "���", "50g", 50, 71, 5, 1000, true);
        Product product12 = new Product(13, ProductType.S_SIDE, 7, "������", "30g", 50, 39, 5, 1200, true);
        Product product13 = new Product(19, ProductType.S_SOURCE, 8, "����", "50g", 50, 239, 5, 500, true);
        r_products2.add(product11);
        r_products2.add(product12);
        r_products2.add(product13);
        masterRcList.add(new MasterRc(2, ProductType.RCMND, "�߻�����", 4000, 2500, r_products2, 10, 50, true));

        Product product21 = new Product(6, ProductType.S_MAIN, 2, "����", "60g", 50, 101, 5, 2500, true);
        Product product22 = new Product(9, ProductType.S_SIDE, 7, "�丶��", "40g", 50, 7, 5, 400, true);
        Product product23 = new Product(20, ProductType.S_SOURCE, 8, "ũ����", "50g", 50, 237, 5, 500, true);
        r_products3.add(product21);
        r_products3.add(product22);
        r_products3.add(product23);
        masterRcList.add(new MasterRc(3, ProductType.RCMND, "���������", 4000, 3000, r_products3, 10, 50, false));
        */
        // [Setting Data] MasterProductList
//        masterRcList.add(new MasterRc(1, ProductType.RCMND, "����ġŲ������", 4000, 3500, new int[]{4,11,19}, 10, 50, true));
//        masterRcList.add(new MasterRc(2, ProductType.RCMND, "�߻�����", 4000, 2500, new int[]{8,13,19}, 10, 50, true));
//        masterRcList.add(new MasterRc(3, ProductType.RCMND, "���������", 4000, 3000, new int[]{6,9,20}, 10, 50, false));

        /*
        // [Test Data] �������
        userSelectList.add(new Order("�̸�1", "20231005153350", 200, 200));
        userSelectList.add(new Order("�̸�2", "20231005153350", 200, 200));
        userSelectList.add(new Order("�̸�3", "20231005153350", 200, 200));
        userSelectList.add(new Order("�̸�4", "20231005153350", 200, 200));
        userSelectList.add(new Order("�̸�5", "20231005153350", 200, 200));
        for (int i = 0; i < 3; i++) {
            userSelectList.get(i).innerList.add(new OrderValues("���̽�", 1, 100, 200));
        }
        */


    }
}