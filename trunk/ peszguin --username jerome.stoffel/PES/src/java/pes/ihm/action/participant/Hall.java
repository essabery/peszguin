package pes.ihm.action.participant;

import static pes.dao.util.HibernateUtil.daoFactory;
import pes.domaine.EStatutTournoi;
import pes.domaine.StatsHallOfFame;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Hall extends ActionSupport {

	private StatsHallOfFame hall;

	public String execute() {
		hall = new StatsHallOfFame(daoFactory.getTournoiDAO().findByStatut(
				EStatutTournoi.TERMINE));
		return SUCCESS;
	}

	public StatsHallOfFame getHall() {
		return hall;
	}

	public void setHall(StatsHallOfFame hall) {
		this.hall = hall;
	}

}
