import java.io.IOException;


/*
 ���� ----------------------------------------------------------------
*/

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // ��ü ������ȭ
        FileMg f = new FileMg();
        MemberMg.hm = f.memberFileIn();
        SalesMg.receipts = f.receiptFileIn();
        CacheData.allProductList = f.list1FileIn();
        CacheData.masterRcList = f.list2FileIn();

        CacheData cacheData = new CacheData();
        cacheData.testData();

        // ������ �α��� ��
        ad_login al = new ad_login();
        al.adLogin();

//        // ��ü ���� ��������
//        f.memberFileOut();
//        f.receiptFileOut();

//        // ȯ���λ� //TODO �ٹж� �ֱ�
//        Emp emp = new Emp(":)");
//        emp.empWelcome();
    }
}
