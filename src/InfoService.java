import java.util.List;

/*
 안내 ----------------------------------------------------------------
*/


public class InfoService implements Imp_info {
    private PdInterface pdInterface;

    // 생성자를 사용하여 productService 초기화
    public InfoService(ProductService productService) {
        this.pdInterface = productService;
    }

    // ProductType을 기준으로 리스트 출력 & 선택 값 저장
    public void printInfo(ProductType productType) {
        if (productType != ProductType.RCMND){    // 사장추천이 아닐 경우
            List<Product> productList = pdInterface.getList(productType);

            // 리스트 출력
            printInfoHeader(productType);
            printInfoBodyProduct(productType, productList);

            // 선택 값 저장
            SelectContinue selectContinue = new SelectContinue();
            selectContinue.menuSelectProduct(productList);
        }else {
            // 리스트 출력
            List<MasterRc> masterList = pdInterface.getListMasterRc(productType);
            printInfoHeader(productType);
            printInfoBodyMaster(productType, masterList);

            // 선택 값 저장
            SelectContinue selectContinue = new SelectContinue();
            selectContinue.menuSelectMasterRc(masterList);
        }
    }

    // 출력 리스트 header
    public void printInfoHeader(ProductType productType) {
        System.out.println("\t---------------------------------------------------------------------------------------------------------------------");
        if(productType != ProductType.RCMND)    // 사장추천이 아닐 경우
            System.out.printf("\t%-4s| %-8s|\t%-8s|\t%-8s|\t%-8s\t|\t%-8s\n", "번호", "상품명", "단위", "칼로리", "가격", "남은수량");
        else
            System.out.printf("\t%-4s| %-8s|\t%-8s|\t%-8s\t|\t%-8s|\t%-8s\n", "번호", "상품명", "칼로리", "가격", "남은수량", "상세재료");
        System.out.println("\t---------------------------------------------------------------------------------------------------------------------");
    }

    // 출력 리스트 body>Mast
    public void printInfoBodyMaster(ProductType productType, List<MasterRc> productInfo) {
        int index=1;
        for(MasterRc masterRc : productInfo){
            System.out.printf("\t%-4d   %-8s \t%-8d \t%-8d \t%-8d", index++, masterRc.getR_name(), masterRc.getR_totalcalorie(), masterRc.getR_price(), masterRc.getR_count());
            System.out.printf("\t");
            // 상세재료 출력
            String materialDetail = "";
            for (int i = 0; i < masterRc.getR_details().length; i++) {
                for (Product p1 : CacheData.allProductList) {
                    if (masterRc.getR_details()[i] == p1.getP_checkNumber()){
                        if (i != 0){
                            materialDetail += ", ";
                        }
                        String materialItem = p1.getP_name();
                        materialDetail += materialItem;
                    }
                }
            }
            System.out.printf("\t %-12s\n", materialDetail);
        }
        System.out.println("\t---------------------------------------------------------------------------------------------------------------------");
    }

    // 출력 리스트 body>Product
    public void printInfoBodyProduct(ProductType productType, List<Product> productInfo) {
        if(productType != ProductType.RCMND){   // 사장추천이 아닐 경우
            int index=1;

            for(Product product : productInfo){
                if(!product.getP_limitFlag()){  // 초기값 셋팅
                    product.setP_limitCount(product.getP_count() - product.getP_stock());
                    product.setP_limitFlag(true);
                }

                System.out.printf("\t%-4d   %-8s \t%-8s \t%-8s\t \t%-8d \t%-8d\n",
                    index++, product.getP_name(), product.getP_unit(), product.getP_calorie(), product.getP_price(), product.getP_limitCount());
            }
			System.out.println("\t---------------------------------------------------------------------------------------------------------------------");
        }
    } // end: printInfoBodyProduct
}