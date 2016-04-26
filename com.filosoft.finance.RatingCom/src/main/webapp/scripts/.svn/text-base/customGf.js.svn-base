
function openDisclaimer(url) {window.open(url); } 
function onLoadRatin(isButtonDisabled) {
	//alert('hello');
	if (!isButtonDisabled) {
		return;
	}
	
    try {
		var numberNoktaPanel = document.getElementById('degerlendirme:noktaH').value;
	} catch (e) {
		// TODO: handle exception
		return;
	}
	
	
	if(numberNoktaPanel==null){
		return;
	}
	drawNoktaTrendGraghicsA(false);
}

function drawNoktaTrendGraghics() {
   drawNoktaTrendGraghicsA(true);
	
}
function drawNoktaTrendGraghicsA(animated) {

	var numberNoktaPanel = document.getElementById('degerlendirme:noktaH').value;
	var numberTendPanel = document.getElementById('degerlendirme:trendH').value;
	var i = 0;
	for (i = 0; i < numberNoktaPanel; i++) {
		var onceki = document.getElementById('degerlendirme:o' + i).value;
		var cari = document.getElementById('degerlendirme:b' + i).value;

		var paper = new Raphael(document.getElementById('degerlendirme:noktaG'+ i), 101, 20);

		
		
	

		paper.rect(0, 0, 100, 18);
		
		
		var oncekibar = paper.path("M0 9 L1 9 L0 0 L0 0 z");

		var sonrakibar = paper.path("M0 18 L1 18 L0 9 L0 9 z");

		var pathonceki = "M0 9 L" + onceki * 10 + " 9  L" + onceki * 10
		+ " 0 L0 0 z";
var pathcari = "M0 18 L" + cari * 10 + " 18  L" + cari * 10
		+ " 9 L0 9 z";

	
		if(animated){
			oncekibar.animate({
				path : pathonceki
			}, 900);
			sonrakibar.animate({
				path : pathcari
			}, 900);
	
	
		}else{
			
			oncekibar.animate({
				path : pathonceki
			}, 0);
			sonrakibar.animate({
				path : pathcari
			}, 0);
	

	
		}
		oncekibar.attr({

			gradient : '90-#526c7a-#64a0c1',
			stroke : '#000000',
			'stroke-width' : 1,
			title:'Onceki Donem'
		});
		sonrakibar.attr({
			gradient : '90-#6eac2c-#80e073',
			stroke : '#000000',
			'stroke-width' : 1,
			title:'Sonraki Donem'  
		});
		var idealbar = paper.rect(70, 0, 4, 18);
		idealbar.attr({
			stroke : 'yes',
			fill : 'black',
			title:'ideal Oran'	
		});
		document.getElementById('degerlendirme:noktaG'+ i).setAttribute("style", "width:105px; height:21px;");
	}

	for (i = 0; i < numberTendPanel; i++) {
		var color = document.getElementById('degerlendirme:c' + i).value;
		var paper = new Raphael(document.getElementById('degerlendirme:trendG'+ i), 20, 20);
		var cir = paper.circle(9, 9, 9);
		
	
		if (color =='red') {
			cir.attr({

				gradient : '90-#a51817-#b785c8',
				stroke : '#000000',
				'stroke-width' : 1

			});
		}
		if (color =='yellow') {
			cir.attr({
				
								gradient : '90-#ecfb5b-#feffa2',
								stroke : '#000000',
								'stroke-width' : 1

							});
		}
		if (color =='green') {
			cir.attr({
				 
								gradient : '90-#609c21-#72d168',
								stroke : '#000000',
								'stroke-width' : 1

							});
						
		}

		document.getElementById('degerlendirme:trendG'+ i).setAttribute("style", "width:20px; height:21px;");
	
		
	}
}


