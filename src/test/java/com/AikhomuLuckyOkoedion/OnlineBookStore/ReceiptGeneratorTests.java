package com.AikhomuLuckyOkoedion.OnlineBookStore;



import com.AikhomuLuckyOkoedion.OnlineBookStore.util.ReceiptGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptGeneratorTests {

    private final ReceiptGenerator receiptGenerator = new ReceiptGenerator();

    @Test
    void testGenerateReceipt() throws IOException {
        String userId = "testUser";
        String items = "[{\"title\":\"Book1\", \"price\":10.0}]";

        byte[] pdf = receiptGenerator.generateReceipt(userId, items);

        assertNotNull(pdf, "Generated PDF should not be null");
        assertTrue(pdf.length > 0, "PDF content should not be empty");
    }
}

