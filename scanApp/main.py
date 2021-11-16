import cv2.cv2 as cv
import numpy as np
from pyzbar.pyzbar import decode

url = "http://192.168.1.99:8080/video"


def decoder(image):
    gray_img = cv.cvtColor(image, 0)
    barcode = decode(gray_img)

    for obj in barcode:
        points = obj.polygon
        (x, y, w, h) = obj.rect
        pts = np.array(points, np.int32)
        pts = pts.reshape((-1, 1, 2))
        cv.polylines(image, [pts], True, (0, 255, 255), 3)
        barcode_data = obj.data.decode("utf-8")
        barcode_type = obj.type
        string = "Data " + str(barcode_data) + " | Type " + str(barcode_type)
        cv.putText(frame, string, (x, y), cv.FONT_HERSHEY_SIMPLEX, 0.8, (255, 0, 0), 2)
        print("Barcode : " + barcode_data + " | Type : " + barcode_type)


cap = cv.VideoCapture(url)
while True:
    camera, frame = cap.read()
    if frame is not None:
        decoder(frame)
        width, height, layers = frame.shape
        nHeight = height / 2
        nWidth = width / 2
        nResizedFrame = cv.resize(frame, (int(nHeight), int(nWidth)))
        cv.imshow("Video", nResizedFrame)
    q = cv.waitKey(1)
    if q == ord("q"):
        break
cv.destroyAllWindows()
