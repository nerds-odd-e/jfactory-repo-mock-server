package com.github.leeonky.dal.extensions;

import com.github.leeonky.dal.DAL;
import com.github.leeonky.dal.runtime.Extension;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.util.zip.GZIPInputStream;

public class GzipExtension implements Extension {
    @Override
    public void extend(DAL dal) {
        dal.getRuntimeContextBuilder().registerStaticMethodExtension(GzipExtension.class);
    }

    @SneakyThrows
    public static byte[] ungzip(byte[] data) {
        return IOUtils.toByteArray(new GZIPInputStream(new ByteArrayInputStream(data)));
    }
}
