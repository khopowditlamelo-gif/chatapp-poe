package chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    // Fresh messages are created for every test, so no setUp() is needed.
    private final Message m1 = new Message("0012345678", 0, "+27718693002",
            "Hi Mike, can you join us for dinner tonight?");
    private final Message m2 = new Message("0098765432", 1, "08575975889",
            "Hi Keegan, did you receive the payment?");

    @Test
    void messageLengthSuccess() {
        assertEquals("Message ready to send.", Message.checkMessageLength("short message"));
    }

    @Test
    void messageLengthFailure() {
        String longMsg = "a".repeat(260);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.",
                Message.checkMessageLength(longMsg));
    }

    @Test
    void recipientSuccess() {
        assertEquals("Cell phone number successfully captured.", m1.checkRecipientCell());
    }

    @Test
    void recipientFailure() {
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                m2.checkRecipientCell());
    }

    @Test
    void hashCorrect() {
        assertEquals("00:0:HITONIGHT", m1.getMessageHash());
    }

    @Test
    void allHashesInLoop() {
        String[] ids = {"0012345678", "0098765432"};
        String[] recipients = {"+27718693002", "08575975889"};
        String[] texts = {
            "Hi Mike, can you join us for dinner tonight?",
            "Hi Keegan, did you receive the payment?"
        };
        String[] expected = {"00:0:HITONIGHT", "00:1:HIPAYMENT"};

        for (int i = 0; i < texts.length; i++) {
            Message m = new Message(ids[i], i, recipients[i], texts[i]);
            assertEquals(expected[i], m.getMessageHash());
        }
    }

    @Test
    void messageIdCreated() {
        assertTrue(m1.checkMessageID());
        assertTrue(new Message(0, "+27718693002", "Hello there").checkMessageID());
    }

    @Test
    void messageSentOptions() {
        assertEquals("Message successfully sent.", m1.sentMessage(1));
        assertEquals("Press 0 to delete the message.", m2.sentMessage(2));
        assertEquals("Message successfully stored.", m1.sentMessage(3));
    }

    @Test
    void totalMessages() {
        Message.reset();
        m1.sentMessage(1);
        m2.sentMessage(2);
        assertEquals(1, Message.returnTotalMessages());
    }
}