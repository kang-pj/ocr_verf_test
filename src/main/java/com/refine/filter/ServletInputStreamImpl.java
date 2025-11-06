package com.refine.filter;

import javax.servlet.ServletInputStream;
import javax.servlet.ReadListener;
import java.io.IOException;

public class ServletInputStreamImpl extends ServletInputStream {
    
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        // Implementation
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}