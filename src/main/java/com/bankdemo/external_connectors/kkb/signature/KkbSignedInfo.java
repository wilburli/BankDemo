package com.bankdemo.external_connectors.kkb.signature;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Ilyas.Kuanyshbekov on 26.09.2016.
 */
@XmlRootElement(name = "SignedInfo")
public class KkbSignedInfo {

    @XmlElement(name = "CanonicalizationMethod")
    private CanonicalizationMethod canonicalizationMethod;

    @XmlElement(name = "SignatureMethod")
    private SignatureMethod signatureMethod;

    @XmlElement(name = "Reference")
    private Reference reference;

    public KkbSignedInfo() {
    }

    @XmlRootElement(name = "CanonicalizationMethod")
    public static class CanonicalizationMethod {

        @XmlAttribute(name = "Algorithm")
        private String algorithm;

        public CanonicalizationMethod() {
        }

        public CanonicalizationMethod(String algorithm) {
            this.algorithm = algorithm;
        }

    }

    @XmlRootElement(name = "SignatureMethod")
    public static class SignatureMethod {

        @XmlAttribute(name = "Algorithm")
        private String algorithm;

        public SignatureMethod() {
        }

        public SignatureMethod(String algorithm) {
            this.algorithm = algorithm;
        }
    }

    @XmlRootElement(name = "Reference")
    public static class Reference {

        @XmlAttribute(name = "URI")
        private String uri;

        @XmlElement(name = "Transforms")
        private Transforms transforms;

        @XmlElement(name = "DigestMethod")
        private DigestMethod digestMethod;

        @XmlElement(name = "DigestValue")
        private String digestValue;

        public Reference() {
        }

        @XmlRootElement(name = "Transform")
        public static class Transform {

            @XmlAttribute(name = "Algorithm")
            private String algorithm;

            public Transform() {
            }

            public Transform(String algorithm) {
                this.algorithm = algorithm;
            }
        }

        @XmlRootElement(name = "DigestMethod")
        public static class DigestMethod {

            @XmlAttribute(name = "Algorithm")
            private String algorithm;

            public DigestMethod() {
            }

            public DigestMethod(String algorithm) {
                this.algorithm = algorithm;
            }
        }

        @XmlRootElement(name = "Transforms")
        public static class Transforms {

            @XmlElement(name = "Transform")
            private List<Transform> transforms;

            public Transforms() {
            }

            public Transforms(List<Transform> transforms) {
                this.transforms = transforms;
            }
        }
    }
}
