package pedidos;

public enum Propina {

    //CLAVE-VALOR
    DOS_PORCIENTO {
        @Override
        public float aplicar(float total) {
            return total * 0.02f;
        }
    },
    TRES_PORCIENTO {
        @Override
        public float aplicar(float total) {
            return total * 0.03f;
        }
    },
    CINCO_PORCIENTO {
        @Override
        public float aplicar(float total) {
            return total * 0.05f;
        }
    };


    public abstract float aplicar(float total);
}
