<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/tetris.css">
<title>Tetris</title>
</head>
<body>

<canvas id="tetris"></canvas>

<div class="option-box">
	<a href="home.us">
      <img src="img/logo.png" class="logo-img"><span class="logo-title">REturn Market</span>
    </a>
	Score: <label id="scoreText">0</label>&nbsp;
	Level: <label id="levelText">1</label>
	<a href="home.us">메인으로</a>
</div>

<div class="key-box">
	Key: (⬅ left) (right ➡) (up 🔃) (down ⬇)<br/>(enter ⏬) New Game = F5
</div>

<script type="text/javascript">

var height=20;
var width=10;
var bheight=4;
var bwidth=4;
var xmargin=4;
var ymargin=4;
var gs=20; // grid size
var field; // map array which value is 0 for empty, 1~7 for filled, 10 for wall
var block;
var bx=by=1;
var xv=yv=0;
var speed=1;
var landed = false;
var gameInterval;
var moveDownInterval;
var line=[];
var score = 0;
var level = 1;

window.onload=function(){
	canv=document.getElementById("tetris");	
	ctx=canv.getContext("2d");	
	
	// background
	ctx.fillStyle="white";
	canv.width=(width+2)*gs;
	canv.height=(height+2)*gs;
	ctx.fillRect(0,0,canv.width, canv.height);
	
	document.addEventListener("keydown",keyPush);
	makeFieldArray();
	makeBlockArray();
	for(var i=0;i<height;i++)
		line.push(0);
	gameInterval = setInterval(game,1000/15);
	moveDownInterval = setInterval(function(){moveDownBlock(1);},1000/speed);
}

function game(){
	
	// level up -> speed up
	if(score>5000*level){
		speed++;
		clearInterval(moveDownInterval);
		moveDownInterval = setInterval(function(){moveDownBlock(1);},1000/speed);
		level++;
		document.getElementById("levelText").innerHTML = level;
	}		
	if(landed){
		landCheck();
		drawingGame();
		refreshGrid();
		var check = checkOverlap(bx,by,block);
		if(check == "overlapped")
			gameOver();
	}else{
		moveDownBlock(yv);
		
		var bxnext = bx+xv;
		var check = checkOverlap(bxnext,by,block);
		if(check == "empty")
			bx=bxnext;
		putBlockinField();
		drawingGame();
		refreshGrid();
	}	
	
	xv=0;
	yv=0;
		
}

function gameOver(){
	alert("Game Over!");
	clearInterval(gameInterval);
	clearInterval(moveDownInterval);
	
}

function imidiateLanding(){
	while(!landed)
		moveDownBlock(1);	
}

function landCheck(){	
		for(var i=0;i<bheight;i++)
			for(var j=0;j<bwidth;j++)
				if(block[i][j]!=0){
					field[i+by][j+bx+2]=-1;
					line[i+by-1]++;					
				}
		clearLine();						
		
		makeBlockArray();		
		bx=by=1;		
		landed = false;		
}

function clearLine(){
	for(var i=0;i<line.length;i++)
		if(line[i]==10){
			score += 1000;
			for(var k=i;k>=1;k--)
				for(var j=1;j<width+1;j++){
					field[k+1][j+2]=field[k][j+2];
					line[k]=line[k-1];
					
					document.getElementById("scoreText").innerHTML = score;
				}
			line[0]=0;
		}
}

function checkOverlap(xx,yy,blockArray){
	for(var i=0;i<bheight;i++)
		for(var j=0;j<bwidth;j++){
			if(field[i+yy][j+xx+2]!=0 && blockArray[i][j]!=0){
				//document.getElementById("text2").innerHTML = "overlap";
				return "overlapped";
			}			
		}
	//document.getElementById("text2").innerHTML = "empty";
	return "empty";
}

function putBlockinField(){
	for(var i=0;i<bheight;i++)
		for(var j=0;j<bwidth;j++)
			if(field[i+by][j+bx+2]==0)
				field[i+by][j+bx+2]=block[i][j];
}

function drawingGame(){										
		
	// drawing game in real-time
	for(var i=1;i<height+1;i++)
		for(var j=1;j<width+1;j++){
			if(field[i][j+2]==0)
				printGrid(j,i,"white");
			else if(field[i][j+2]==1)
				printGrid(j,i,"yellow");
			else if(field[i][j+2]==2)
				printGrid(j,i,"green");
			else if(field[i][j+2]==3)
				printGrid(j,i,"blue");
			else if(field[i][j+2]==4)
				printGrid(j,i,"red");
			else if(field[i][j+2]==5)
				printGrid(j,i,"orange");
			else if(field[i][j+2]==6)
				printGrid(j,i,"purple");
			else if(field[i][j+2]==7)
				printGrid(j,i,"brown");
			else if(field[i][j+2]==10)
				printGrid(j,i,"black");
			else if(field[i][j+2]==-1)
				printGrid(j,i,"gray");
		}
}

function moveDownBlock(yy){
	if(yy!=0){
		var bynext = by+yy;
		var check = checkOverlap(bx,bynext,block);
		if(check == "empty")
			by=bynext;
		else if(check == "overlapped"){
			landed = true;
		}
	}		
}

function refreshGrid(){
	for(var i=1; i<height+1; i++){
		for(var j=1; j<width+1; j++){
			if(field[i][j+2]>0)
				field[i][j+2]=0;
		}
	}
}

function keyPush(evt){
	switch(evt.keyCode){
	case 37: // left
		xv=-1;
		break; 
	case 38: // up
		rotate();
		break;
	case 39: // right
		xv=1;
		break;
	case 40: // down
		yv=1;
		break;
	case 13: // enter
		imidiateLanding();
		break;
	}	
}

function makeFieldArray(){
	field = new Array(height+2+ymargin);
	for(var i=0; i<field.length; i++){
		field[i] = new Array(width+2+xmargin);
	}
	for(var i=0; i<height+2; i++){
		for(var j=0; j<width+2; j++){
			if(i==0 || j==0 || i==height+1 || j==width+1)
				field[i][j+2]=10;
			else
				field[i][j+2]=0; 
		}
	}
}

function makeBlockArray(){
	block = new Array(bheight);
	for(var i=0; i<block.length; i++){
		block[i] = new Array(bwidth);
	}
	for(var i=0; i<block.length; i++){
		for(var j=0; j<block[i].length; j++){
			block[i][j]=0; 
		}
	}
	createRandomBlock();
}

function createRandomBlock(){
	/* Random Move */
	key = Math.floor(Math.random()*7);
	switch(key){
	case 0: // ㅡ component
		block[0][0]=block[0][1]=block[0][2]=block[0][3]=1;
		break;
	case 1: // ㅁ
		block[0][0]=block[0][1]=block[1][0]=block[1][1]=2;
		break;
	case 2: // ㄱ
		block[0][0]=block[0][1]=block[0][2]=block[1][2]=3;
		break;
	case 3: // ㄴ
		block[0][0]=block[1][0]=block[1][1]=block[1][2]=4;
		break;
	case 4: // ㄹ
		block[0][0]=block[0][1]=block[1][1]=block[1][2]=5;
		break;
	case 5: // s
		block[1][0]=block[1][1]=block[0][1]=block[0][2]=6;
		break;
	case 6: // ㅗ
		block[0][0]=block[0][1]=block[0][2]=block[1][1]=7;
		break;
	}
}

function printGrid(xx,yy,color){
	ctx.fillStyle=color;
	ctx.fillRect(xx*gs+1,yy*gs+1,gs-2, gs-2);
}

function rotate(){
	// create new block
	var rotatedblock = new Array(bheight);	
	for(var i=0; i<block.length; i++){
		rotatedblock[i] = new Array(bwidth);
	}
	// clockwise rotation
	for(var i=0; i<block.length; i++){
		for(var j=0; j<block[i].length; j++){
			rotatedblock[j][3-i]=block[i][j];
		}
	}
	if(checkOverlap(bx,by,rotatedblock)=="empty")	
		block = rotatedblock;
}


</script>

</body>
</html>