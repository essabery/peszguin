package pes.ihm.action.participant;

import pes.dao.ITournoiDAO;
import pes.domaine.EStatutTournoi;
import pes.domaine.StatsHallOfFame;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Hall extends ActionSupport {

	private StatsHallOfFame hall;

	private ITournoiDAO tournoiDAO;

	public String execute() {
		hall = new StatsHallOfFame(
				tournoiDAO.findByStatut(EStatutTournoi.TERMINE));
		return SUCCESS;
	}

	public StatsHallOfFame getHall() {
		return hall;
	}

	public void setHall(StatsHallOfFame hall) {
		this.hall = hall;
	}

}
