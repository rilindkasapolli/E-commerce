package reddit.example.simpleforumgmail.config;

import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebFilter({"/*"})
public class HitCounterFilter implements Filter {
    public HitCounterFilter() {
    }

    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletContext context = request.getServletContext();
        String realWebAppPath = context.getRealPath("");
        String hitFilePath = realWebAppPath.concat("hit.txt");
        File hitFile = new File(hitFilePath);
        long currentHit = this.readHitCounterFromFile(hitFile);
        this.updateHitCounterFile(++currentHit, hitFile);
        CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse)response);
        chain.doFilter(request, wrapper);
        int onlineUsers = (Integer)context.getAttribute("OnlineUsers");
        this.displayHitCounter(wrapper, response, currentHit, onlineUsers);
    }

    private long readHitCounterFromFile(File hitFile) throws Throwable {
        if (!hitFile.exists()) {
            return 0L;
        } else {
            Throwable var2 = null;
            Object var3 = null;

            try {
                BufferedReader reader = new BufferedReader(new FileReader(hitFile));

                long var10000;
                try {
                    long hit = (long)Integer.parseInt(reader.readLine());
                    var10000 = hit;
                } finally {
                    if (reader != null) {
                        reader.close();
                    }

                }

                return var10000;
            } catch (Throwable var12) {
                if (var2 == null) {
                    var2 = var12;
                } else if (var2 != var12) {
                    var2.addSuppressed(var12);
                }

                throw var2;
            }
        }
    }

    private void updateHitCounterFile(long hit, File hitFile) throws Throwable {
        Throwable var4 = null;
        Object var5 = null;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(hitFile));

            try {
                writer.write(String.valueOf(hit));
            } finally {
                if (writer != null) {
                    writer.close();
                }

            }

        } catch (Throwable var12) {
            if (var4 == null) {
                var4 = var12;
            } else if (var4 != var12) {
                var4.addSuppressed(var12);
            }

            throw var4;
        }
    }

    private void displayHitCounter(CharResponseWrapper wrapper, ServletResponse response, long currentHit, int onlineUsers) throws IOException {
        PrintWriter writer = response.getWriter();
        if (wrapper.getContentType().contains("text/html")) {
            CharArrayWriter caw = new CharArrayWriter();
            String originalContent = wrapper.toString();
            int indexOfCloseBodyTag = originalContent.indexOf("</body>") - 1;
            caw.write(originalContent.substring(0, indexOfCloseBodyTag));
            String hitCounterContent = "<p>Online Users: " + onlineUsers + " - Pageviews: " + currentHit + "</p>";
            caw.write(hitCounterContent);
            caw.write("\n</body></html>");
            String alteredContent = caw.toString();
            response.setContentLength(alteredContent.length());
            writer.write(alteredContent);
        } else {
            writer.write(wrapper.toString());
        }

        writer.close();
    }
}
