package com.dev.project.service;

import org.springframework.stereotype.Service;

@Service
public class NFEService {

    private static final String urlEmissao = "https://webmaniabr.com/api/1/nfe/emissao/";
    private static final String urlConsultaNfe = "https://webmaniabr.com/api/1/nfe/consulta/?chave=";
    private static final String urlCartaCorrecao = "https://webmaniabr.com/api/1/nfe/cartacorrecao/";
    private static final String urlCancelamentoNfe = "https://webmaniabr.com/api/1/nfe/cancelar/";


    public String emitirNfe(){
        String consumerKey = "";
        String consumerSecret = "";
        String accessToken = "";
        String accessTokenSecret = "";


//        String operacao = "";
//
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\n\t\"ID\": 1137,\n\t\"url_notificacao\": \"http://meudominio.com/retorno.php\",\n\t\"operacao\": "+operacao+",\n\t\"natureza_operacao\": \"Venda de produção do estabelecimento\",\n\t\"modelo\": 1,\n\t\"finalidade\": 1,\n\t\"ambiente\": 2,\n\t\"cliente\": {\n\t\t\"cpf\": \"000.000.000-00\",\n\t\t\"nome_completo\": \"Nome do Cliente\",\n\t\t\"endereco\": \"Av. Brg. Faria Lima\",\n\t\t\"complemento\": \"Escritório\",\n\t\t\"numero\": 1000,\n\t\t\"bairro\": \"Itaim Bibi\",\n\t\t\"cidade\": \"São Paulo\",\n\t\t\"uf\": \"SP\",\n\t\t\"cep\": \"00000-000\",\n\t\t\"telefone\": \"(00) 0000-0000\",\n\t\t\"email\": \"nome@email.com\"\n\t},\n\t\"produtos\": [{\n\t\t\"nome\": \"Nome do produto\",\n\t\t\"codigo\": \"nome-do-produto\",\n\t\t\"ncm\": \"6109.10.00\",\n\t\t\"cest\": \"28.038.00\",\n\t\t\"quantidade\": 3,\n\t\t\"unidade\": \"UN\",\n\t\t\"peso\": \"0.800\",\n\t\t\"origem\": 0,\n\t\t\"subtotal\": \"44.90\",\n\t\t\"total\": \"134.70\",\n\t\t\"classe_imposto\": \"REF1000\"\n\t}, \n\t{\n\t\t\"nome\": \"Nome do produto\",\n\t\t\"codigo\": \"nome-do-produto\",\n\t\t\"ncm\": \"6109.10.00\",\n\t\t\"cest\": \"28.038.00\",\n\t\t\"quantidade\": 1,\n\t\t\"unidade\": \"UN\",\n\t\t\"peso\": \"0.800\",\n\t\t\"origem\": 0,\n\t\t\"subtotal\": \"29.90\",\n\t\t\"total\": \"29.90\",\n\t\t\"classe_imposto\": \"REF1000\"\n\t}],\n\t\"pedido\": {\n\t\t\"pagamento\": 0,\n\t\t\"presenca\": 2,\n\t\t\"modalidade_frete\": 0,\n\t\t\"frete\": \"12.56\",\n\t\t\"desconto\": \"10.00\",\n\t\t\"total\": \"174.60\"\n\t}\n}");
//        Request request = new Request.Builder()
//                .url(""+urlEmissao+"")
//                .method("POST", body)
//                .addHeader("Cache-Control", "no-cache")
//                .addHeader("Content-Type", "application/json")
//                .addHeader("X-Consumer-Key", ""+consumerKey+"")
//                .addHeader("X-Consumer-Secret", ""+consumerSecret+"")
//                .addHeader("X-Access-Token", ""+accessToken+"")
//                .addHeader("X-Access-Token-Secret", ""+accessTokenSecret+"")
//                .addHeader("Cookie", "PHPSESSID=j4ihn95of8r4hcih3o00d77lku")
//                .build();
//        Response response = client.newCall(request).execute();

        return null;
    }

}
