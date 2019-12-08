
/*

Задать трехмерную модель точками и сделать ее вращение, масштабирование, перемещение
Освещение не менее 3 прожекторами
Несколько режимов работы
1)Проволочная модель
2)Закрашенная модель
А) простая закраска
Б) закраска методом гуго?
В) закраска методом фонга?


	private Vector3f barycentric(Vector2f[] pts, Vector2f P, Vector3f ptso[]) {
		Vector3f u = mult(
				new Vector3f(sub(pts[2], pts[0]).getX(), sub(pts[1], pts[0]).getX(), sub(pts[0], P).getX()),
				new Vector3f(sub(pts[2], pts[0]).getY(), sub(pts[1], pts[0]).getY(), sub(pts[0], P).getY()));
	    if (abs(u.getZ()) > 1) {
	    	return new Vector3f(-1,1,1); // Triangle is degenerate, in this case return smth with negative coordinates
	    }
	    Vector3f bcScreen = new Vector3f(1.0f - (u.getX()+u.getY())/u.getZ(), u.getY()/u.getZ(), u.getX()/u.getZ());
	    Vector3f tmp = new Vector3f(bcScreen.getX() / ptso[0].getZ(), bcScreen.getY() / ptso[1].getZ(), bcScreen.getZ() / ptso[2].getZ());
	    float Pz = 1 / (tmp.getX() + tmp.getY() + tmp.getZ());
	    Vector3f bsClip = mult(tmp, Pz);
	    return bsClip;
	}



*/