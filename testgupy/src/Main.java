import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.io.FileReader;
import java.io.IOException;

public class FaturamentoMensal {
    public static <JsonObject> void main(String[] args) {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader("faturamento.json");
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonArray faturamentoArray = jsonObject.getAsJsonArray("faturamento");

            double maiorFaturamento = Double.MIN_VALUE;
            double menorFaturamento = Double.MAX_VALUE;
            double somaFaturamento = 0;
            int diasValidos = 0;

            for (int i = 0; i < faturamentoArray.size(); i++) {
                JsonObject dia = faturamentoArray.get(i).getAsJsonObject();
                double valor = dia.get("valor").getAsDouble();

                if (valor > 0) {
                    somaFaturamento += valor;
                    diasValidos++;

                    if (valor > maiorFaturamento) {
                        maiorFaturamento = valor;
                    }
                    if (valor < menorFaturamento) {
                        menorFaturamento = valor;
                    }
                }
            }

            double mediaFaturamento = somaFaturamento / diasValidos;

            int diasAcimaMedia = 0;
            for (int i = 0; i < faturamentoArray.size(); i++) {
                JsonObject dia = faturamentoArray.get(i).getAsJsonObject();
                double valor = dia.get("valor").getAsDouble();

                if (valor > mediaFaturamento) {
                    diasAcimaMedia++;
                }
            }

            System.out.println("Maior faturamento diário: " + maiorFaturamento);
            System.out.println("Menor faturamento diário: " + menorFaturamento);
            System.out.println("Média mensal de faturamento (dias válidos): " + mediaFaturamento);
            System.out.println("Dias com faturamento acima da média: " + diasAcimaMedia);

            reader.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo JSON: " + e.getMessage());
        }
    }
}
