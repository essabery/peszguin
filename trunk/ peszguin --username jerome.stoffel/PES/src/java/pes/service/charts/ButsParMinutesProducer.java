package pes.service.charts;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.jfree.data.general.DefaultPieDataset;

import pes.domaine.Evenement;
import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;

@SuppressWarnings("serial")
public class ButsParMinutesProducer implements DatasetProducer, Serializable {

	public String getProducerId() {
		return "butsParMinutes";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean hasExpired(Map params, Date since) {
		return (System.currentTimeMillis() - since.getTime()) > 1000;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object produceDataset(Map params) throws DatasetProduceException {

		Evenement[] evenements = (Evenement[]) params.get("evenements");
		int nb_0_15 = 0;
		int nb_16_30 = 0;
		int nb_31_45 = 0;
		int nb_46_60 = 0;
		int nb_61_75 = 0;
		int nb_76_90 = 0;
//		int nb_91_105 = 0;
//		int nb_106_120 = 0;

		for (Evenement evenement : evenements) {
			if (evenement != null && evenement.getMinute() != null) {
				if (evenement.getMinute() <= 15) {
					nb_0_15++;
				} else if (evenement.getMinute() <= 30) {
					nb_16_30++;
				} else if (evenement.getMinute() <= 45) {
					nb_31_45++;
				} else if (evenement.getMinute() <= 60) {
					nb_46_60++;
				} else if (evenement.getMinute() <= 75) {
					nb_61_75++;
				} else if (evenement.getMinute() <= 90) {
					nb_76_90++;
				}
//				} else if (evenement.getMinute() <= 105) {
//					nb_91_105++;
//				} else if (evenement.getMinute() <= 120) {
//					nb_106_120++;
//				}
			}
		}

		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("0-15", nb_0_15);
		data.setValue("16-30", nb_16_30);
		data.setValue("31-45", nb_31_45);
		data.setValue("46-60", nb_46_60);
		data.setValue("61-75", nb_61_75);
		data.setValue("76-90", nb_76_90);
//		data.setValue("91-105", nb_91_105);
//		data.setValue("106-120", nb_106_120);

		return data;

	}
}