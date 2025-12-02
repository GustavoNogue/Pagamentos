// MPayAdapter.java
public class MPayAdapter implements PPay {

    private MPay mpay;

    public MPayAdapter(MPay mpay) {
        this.mpay = mpay;
    }

    // --- Getters adaptados ---
    @Override
    public String getCustCardNo() {
        // mapeia para getCreditCardNo()
        return mpay.getCreditCardNo();
    }

    @Override
    public String getCardOwnerName() {
        // mapeia para getCustomerName()
        return mpay.getCustomerName();
    }

    @Override
    public String getCardExpMonthDate() {
        // MPay tem mês e ano separados; PPay quer "MM/YYYY" ou similar
        String month = mpay.getCardExpMonth();
        String year  = mpay.getCardExpYear();
        // Proteções simples caso algum seja nulo
        if (month == null) month = "";
        if (year == null) year = "";
        // Exemplo de formato: "MM/YYYY"
        return month + "/" + year;
    }

    @Override
    public Integer getCVVNo() {
        // MPay retorna Short; PPay espera Integer
        Short cvv = mpay.getCardCVVNo();
        return (cvv == null) ? null : Integer.valueOf(cvv);
    }

    @Override
    public Double getTotalAmount() {
        return mpay.getAmount();
    }

    // --- Setters adaptados (opcional, caso precise criar PPay a partir de MPay) ---
    @Override
    public void setCustCardNo(String custCardNo) {
        mpay.setCreditCardNo(custCardNo);
    }

    @Override
    public void setCardOwnerName(String cardOwnerName) {
        mpay.setCustomerName(cardOwnerName);
    }

    @Override
    public void setCardExpMonthDate(String cardExpMonthDate) {
        // espera-se formato "MM/YYYY" ou "MM/YY" -> divida em month e year
        if (cardExpMonthDate == null) {
            mpay.setCardExpMonth(null);
            mpay.setCardExpYear(null);
            return;
        }
        String[] parts = cardExpMonthDate.split("/");
        if (parts.length >= 2) {
            mpay.setCardExpMonth(parts[0]);
            mpay.setCardExpYear(parts[1]);
        } else {
            // se só veio mês, coloca ano vazio
            mpay.setCardExpMonth(cardExpMonthDate);
            mpay.setCardExpYear("");
        }
    }

    @Override
    public void setCVVNo(Integer cVVNo) {
        mpay.setCardCVVNo(cVVNo == null ? null : cVVNo.shortValue());
    }

    @Override
    public void setTotalAmount(Double totalAmount) {
        mpay.setAmount(totalAmount);
    }
}
