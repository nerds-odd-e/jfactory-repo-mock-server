package com.github.leeonky.dal.extensions;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.leeonky.dal.DAL;
import com.github.leeonky.dal.runtime.Extension;
import lombok.SneakyThrows;

import java.util.Map;

public class XmlExtensions implements Extension {

    @Override
    public void extend(DAL dal) {
        dal.getRuntimeContextBuilder().registerStaticMethodExtension(Methods.class);
    }

    public static class Methods {

        private static final XmlMapper xmlMapper = new XmlMapper();

        public static Object xml(byte[] data) {
            return xml(new String(data));
        }

        @SneakyThrows
        public static Object xml(CharSequence data) {
            return xmlMapper.readValue(data.toString(), Map.class);
        }
    }

}
