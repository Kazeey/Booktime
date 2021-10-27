import cv2.cv2 as cv

url = "http://192.168.1.99:8080/video"
cap = cv.VideoCapture(url)

while(True):
    camera, frame = cap.read()
    if frame is not None:
        width, height, layers = frame.shape
        nHeight = height / 2
        nWidth = width / 2
        nResizedFrame = cv.resize(frame, (int(nHeight), int(nWidth)))
        cv.imshow("Video", nResizedFrame)
    q = cv.waitKey(1)
    if q == ord("q"):
        break
cv.destroyAllWindows()
