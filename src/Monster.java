import java.util.Random;
public class Monster {
Random rando = new Random();

public int Attack;
public int HP;
public int posY;
public int posX;
public int old_posY,old_posX;

public char looks;

public Monster(int type,int x, int y,int x2,int y2) {
	System.out.println(type);
	Attack=(int)(4+type*1.1);
	HP=(int)((8+Math.pow(type, 1.4))*2);
	posX=x;
	posY=y;
	looks=(char)(type+47-Dungeon.ascension*10);
	System.out.println("looks: "+looks);

	
	old_posY=posY;
	old_posX=posX;
}

public void takeDamage(int dmg) {
	HP-=dmg;
}

public void move(int playerX,int playerY) {
	old_posY=posY;
	old_posX=posX;
	//System.out.println("level: "+ Attack+ " posX: " + posX + " posY: " + posY );
	//if both x and y are different
	if(playerX!=posX && playerY!=posY) {
		//System.out.println("none");
		//rolls to see if x or y changes and changes it
		if(rando.nextInt(2)==1)
			if(playerX<posX)
				posX--;
			else
				posX++;
		else
			if(playerY<posY)
				posY--;
			else
				posY++;
	}
	// if x values are equal but not y
	else if(playerX==posX && playerY!=posY) {
	//System.out.println("X=X");
		if(playerY+1!=posY && playerY-1!=posY)
			if(playerY<posY)
				posY--;
			else
				posY++;
	}
		
	// if y values are equal but not x
	else if(playerY==posY && playerX!=posX) {
		//System.out.println("Y=Y");
		if(playerX+1!=posX && playerX-1!=posX)
			if(playerX<posX)
				posX--;
			else
				posX++;
	}
	//System.out.println("level: "+ Attack+ " posX: " + posX + " posY: " + posY );
}
public int getX() {
	return posX;
}
public int getY() {
	return posY;
}
public void unMove() {
	posY=old_posY;
	posX=old_posX;
	System.out.println("level: "+ Attack+ " posX: " + posX + " posY: " + posY );
}
public int getLevel() {
	return Attack;
}

public void Attack(int playerX ,int playerY) {
	if(playerX+1==posX || playerX-1==posX)
		if(playerY==posY)
			Dungeon.playerHP-=Attack-rando.nextInt(Dungeon.playerArmor+1);
	if(playerY+1==posY || playerY-1==posY)
		if(playerX==posX)
			Dungeon.playerHP=Dungeon.playerHP-Attack+rando.nextInt(Dungeon.playerArmor+1);

}

}
