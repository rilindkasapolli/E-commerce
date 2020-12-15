package reddit.example.simpleforumgmail.config;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.PrintWriter;

public class CharResponseWrapper extends HttpServletResponseWrapper {
    private CharArrayWriter writer = new CharArrayWriter();

    public CharResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public PrintWriter getWriter() {
        return new PrintWriter(this.writer);
    }

    public String toString() {
        return this.writer.toString();
    }
}