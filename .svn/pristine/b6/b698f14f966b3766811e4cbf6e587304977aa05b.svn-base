package communication.vehicle.report;


import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import communication.language.Text;

public class ContractComodatoDataSource implements JRDataSource {

    private String[][] values;
    private int index = -1;
	private Text messages;
    
    public ContractComodatoDataSource() {
    	values = new String[][]{{"1ª)","A COMODANTE entrega ao COMODATÁRIO(A) o veículo acima descrito, devidamente licenciado e lacrado, com"},
    			{"","todos os impostos e taxas pagos para livre circulação em todo Território Nacional, inclusive com apólice de seguro"},
    			{"","com cobertura para danos materiais e pessoais, nos termos da política interna da COMODANTE ."},
    			{"",""},
    			{"2ª)","Destina-se o aludido veículo a que o COMODATÁRIO(A) possa exercer suas atividades para a COMODANTE, de "},
    			{"","acordo com as normas internas."},
    			{"",""},
    			{"3ª)","É permitido ao COMODATÁRIO(A) a utilização do aludido bem, de acordo com as condições constantes do"},
    			{"","presente contrato de comodato."},
    			{"",""},
    			{"4ª)","A utilização do veículo por terceiros somente será admitida nas condições descritas no item 7.5 da Política de"},
    			{"","Veículos de Frota Própria, da qual o(a) COMODATÁRIO(A) declara ter pleno conhecimento."},
    			{"",""},
    			{"5ª)","O COMODATÁRIO(A) será reembolsada pelas despesas de utilização do veículo de acordo com as normas"},
    			{"","internas da COMODANTE. "},
    			{"",""},
    			{"6ª)","No caso de ocorrer acidente com o veículo objeto deste contrato, o(a) COMODATÁRIO(A) arcará com o"},
    			{"","pagamento integral do custo da franquia do seguro, ou o valor total para reparo do veículo caso a franquia"},
    			{"","estipulada pelo seguro não seja atingida ou ainda a outros critérios estipulados em normas internas da "},
    			{"","COMODANTE, desde que fique configurada a culpa ou dolo do(a) COMODATÁRIO(A) no acidente."},
    			{"",""},
    			{"7ª)","Havendo perda total ou furto do veículo, a COMODANTE  providenciará a sua substituição."},
    			{"",""},
    			{"8ª)","Os consertos que não sejam de caráter de urgência, bem como reformas totais ou parciais, necessitarão de prévia"},
    			{"","autorização da unidade responsável  pela administração da frota para que sejam executados."},
    			{"",""},
    			{"9ª)","Mensalmente, o(a) COMODATÁRIO(A) deverá apresentar ao seu superior imediato Relatório de Despesas do "},
    			{"","veículo."},
    			{"",""},
    			{"10ª)","Não será permitida a instalação de acessórios / equipamentos adicionais que alterem as características originais"}, 
    			{"","do veículo."},
    			{"",""},
    			{"11ª)","Havendo alteração de cargo para o qual a empresa não conceda veículo, ou, se durante a  vigência deste "},
    			{"","contrato houver rescisão de contrato de trabalho entre as partes, o(a) COMODATÁRIO(A) deverá devolver o "},
    			{"","veículo recebido da COMODANTE , em perfeitas condições de uso, salvo o desgaste natural das peças e "},
    			{"","componentes ."},
    			{"",""},
    			{"12ª)","Em se configurando a má utilização do veículo pelo(a) COMODATÁRIO(A), poderá a COMODANTE pleitear "},
    			{"","ressarcimento das despesas com o conserto das partes do veículo diretamente afetadas por esta condição,ou "},
    			{"","sem qualquer aviso prévio ou notificação, rescindir  o presente contrato e reaver o veículo, sem qualquer "},
    			{"","ressarcimento ou indenização ao COMODATÁRIO(A). "},
    			{"",""},
    			{"13ª)","Reserva-se à COMODANTE o direito de vistoriar o veículo, a qualquer tempo, em data fixada pela unidade "},
    			{"","responsável pela administração da frota, no sentido de verificar se estão sendo cumpridas as cláusulas deste, no "},
    			{"","tocante à conservação do veículo, inclusive a conferência de quilometragem informada no relatório com a  "},
    			{"","registrada no odômetro do veículo."},
    			{"",""},
    			{"14ª)","Definida a venda do veículo pela COMODANTE, terá o(a) COMODATÁRIO(A) preferência  sobre a sua aquisição "},
    			{"","de conformidade com o procedimento vigente na época."},
    			{"",""},
    			{"15ª)","Compromete-se o(a) COMODATÁRIO(A) a conduzir o veículo dentro das leis de trânsito arcando, com multas por "},
    			{"","infrações que forem aplicadas pelas autoridades competentes, bem como a ressarcir a COMODANTE do valor "},
    			{"","das despesas por ela quitadas, através de desconto em folha de pagamento, estando sujeito o(a) "},
    			{"","COMODATÁRIO(A) ao não reembolso das despesas descritas na clausula 5ª até sua quitação, incluindo-se os "},
    			{"","danos com acidentes de trânsito."},
    			{"",""},
    			{"","Único - 	No caso de acidente, roubo ou furto, o(a) COMODATÁRIO(A) deverá providenciar junto aos órgãos "},
    			{"","         locais competentes toda documentação necessária para o processo de reparação."},
    			{"",""},
    			{"16ª)","Correrão por conta da COMODANTE  as despesas com licenciamento e seguro do veículo."},
    			{"",""},
    			{"17ª)","O presente contrato vigorará pelo prazo de 36 meses ou 100.000 km rodados, não sendo permitida a substituição "},
    			{"","do veículo por outro, salvo se o(a) COMODATÁRIO(A) vier a exercer outra atividade para a COMODANTE que, "},
    			{"","de acordo com as normas internas, lhe destine um veículo com outras características."},
    			{"",""},
    			{"18ª)","Poderá a COMODANTE rescindir o presente se o(a) COMODATÁRIO(A) vier a exercer outra atividade para a "},
    			{"","COMODANTE, nos termos das normas internas em vigor."},
    			{"",""},
    			{"19ª)","O COMODATÁRIO(A)  obriga-se a observar as normas que regem o presente contrato ciente de que a sua não "},
    			{"","observância acarretará em sanções previstas de acordo com as normas internas."},
    			{"",""},
    			{"20ª)","As partes elegem o Foro da Comarca de São Paulo para dirimir quaisquer dúvidas da interpretação do presente "},
    			{"","contrato."}};
    }
    
    public boolean next() throws JRException {
        if(index+1 < values.length) {
        	index++;
        	return true;
        }
        return false;
    }

    public Object getFieldValue(JRField field) throws JRException {
    	if(field.getName().equals("index")) {
    		return values[index][0];
    	}
    	if(field.getName().equals("text")) {
    		return values[index][1];
    	}
    	return null;
    }


}
