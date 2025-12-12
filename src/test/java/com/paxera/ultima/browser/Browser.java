package com.paxera.ultima.browser;

import com.paxera.ultima.platform.UltimaViewer;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Browser {
    public UltimaViewer ultimaViewer;

    public Browser() throws IOException, UnsupportedFlavorException {
        ultimaViewer = new UltimaViewer();
    }
}
