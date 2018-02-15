package mz.ciuem.sipma.vm;

import java.io.IOException;
import java.util.List;

import mz.ciuem.sipma.entity.Coin;
import mz.ciuem.sipma.service.CoinService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CoinVM extends AbstractVM {

	private Coin coin;

	private List<Coin> coins;

	@WireVariable
	private CoinService coinService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		Selectors.wireComponents(view, this, false);

	}

	@Init(superclass = true)
	public void init(@ContextParam(ContextType.VIEW) Component view) {

		this.ui = view;

		coin = new Coin();

		coins = coinService.getAll();
	}

	@NotifyChange({ "coin", "coins" })
	@Command
	public void save() {

		coinService.create(coin);
		log("Reportou nova Fonte de Orçamento: " + coin.getCoinInitials());
		Clients.showNotification("Fonte #" + coin.getCoinInitials() + " registado com sucesso!", "info", ui,
				"before_center", -1);

		coin = new Coin();

		coins = coinService.getAll();
		refreshDataTable(null);
	}
	
	@NotifyChange("coin")
	@Command
	public void edit(@BindingParam("coinID") Long coinID) {

		coin = coinService.find(coinID);

		editButtons();

	}

	@NotifyChange({ "coin", "coins" })
	@Command
	public void update() {

		coinService.update(coin);

		log("Actualizou os dados da Moeda: " + coin.getCoinInitials());
		Clients.showNotification("Moeda Actualizada com sucesso!", "info", ui, "before_center", -1);

		refreshDataTable(null);

		cancelButtons();

	}


	public List<Coin> getCoins() {
		return coins;
	}

	public void setCoins(List<Coin> coins) {
		this.coins = coins;
	}

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

}
