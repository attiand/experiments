package test;

import org.junit.jupiter.api.Test;

public class TextBlockFormatting {

    @Test
    void shouldPrintString() {
        String sql = """                    
                update %s
                    
                set next_feed_id = ?, prev_feed_id = ?, feed_xml = xmlparse( document cast(? as text) preserve whitespace)
                    
                where feed_id = ?
                    
                """;
        System.out.println(sql);
    }
}
