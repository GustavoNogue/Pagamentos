// PaymentAdapterTest.java
public class PaymentAdapterTest {

    public static void testPPay(PPay pp){
        System.out.println(pp.getCardOwnerName());
        System.out.println(pp.getCustCardNo());
        System.out.println(pp.getCardExpMonthDate());
        System.out.println(pp.getCVVNo());
        System.out.println(pp.getTotalAmount());
    }

    public static void main(String[] args) {
        // Cria uma implementação MPay (fornecida no seu zip: MpayImpl)
        MpayImpl mpay = new MpayImpl();
        mpay.setCreditCardNo("4111222233334444");
        mpay.setCustomerName("Gustavo Nogueira");
        mpay.setCardExpMonth("05");
        mpay.setCardExpYear("2026");
        mpay.setCardCVVNo((short)123);
        mpay.setAmount(2500.75);

        // Cria o adapter que transforma MPay em PPay
        PPay adapter = new MPayAdapter(mpay);

        // Testa usando o método pedido
        testPPay(adapter);
    }
}
